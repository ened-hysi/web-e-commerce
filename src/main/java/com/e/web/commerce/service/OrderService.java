package com.e.web.commerce.service;


import com.e.web.commerce.Dto.*;
import com.e.web.commerce.Entity.Orders;
import com.e.web.commerce.Entity.OrderItems;
import com.e.web.commerce.Entity.Product;
import com.e.web.commerce.Enum.OrderStatus;
import com.e.web.commerce.Enum.PaymentStatus;
import com.e.web.commerce.repository.OrderItemsRepository;
import com.e.web.commerce.repository.OrderRepository;
import com.e.web.commerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private OrderItemsRepository orderItemsRepository;

    private ModelMapper modelMapper = new ModelMapper();


    public Orders createOrder(OrderDto orderDto) {

        Orders orders = this.modelMapper.map(orderDto, Orders.class);
        orders.setId(null);
        orders.setOrderStatus(OrderStatus.PROCESSING);
        Orders result = this.orderRepository.save(orders);


        List<Long> productIds = orderDto.getOrderItems().stream().map(
                orderItemsDto -> orderItemsDto.getProductId()
        ).collect(Collectors.toList());

        List<Product> productList = this.productRepository.findByIdIn(productIds);

        Map<Long, Integer> mapProduct = new HashMap<>();

        for (OrderItemsDto itemsDto: orderDto.getOrderItems()) {
            mapProduct.put(itemsDto.getProductId(), itemsDto.getQuantity());
        }


        Double totalAmount = 0D;

        for (Product product: productList) {
            OrderItems orderItems = new OrderItems();
            orderItems.setOrders(result);
            orderItems.setProduct(product);
            orderItems.setPrice(product.getPrice());
            orderItems.setQuantity(mapProduct.get(product.getId()));
            orderItems.setSubTotal(orderItems.getPrice() * orderItems.getQuantity());
            totalAmount+= orderItems.getSubTotal();

            if(product.getQuantity() < orderItems.getQuantity()) {
                throw new InputMismatchException("You cannot order this quantity because we have low stock.");
            }
            orderItemsRepository.save(orderItems);
            product.setQuantity(product.getQuantity() - orderItems.getQuantity());
            productRepository.save(product);
        }

        result.setTotalAmount(totalAmount);
        result.setOrderItems(null);
        return orderRepository.save(result);
    }

    public Orders updateOrder(Long id, UpdateOrderDto orderDto) {

        //FInd existing existingOrder
        Orders existingOrder = this.findById(id);

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(orderDto, existingOrder);

        return orderRepository.save(existingOrder);

    }

    public Orders addOrderItems(Long orderId, OrderItemsUpdateDto orderItemsUpdateDto) {

        Orders existing = this.findById(orderId);

        if(existing.getPaymentStatus() == PaymentStatus.PAYED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot add new Items into a payed Order");
        }

        List<Long> productIds = orderItemsUpdateDto.getOrderItems().stream().
                map(orderItemsDto -> orderItemsDto.getProductId())
                .collect(Collectors.toList());


        List<Product> productList = this.productRepository.findByIdIn(productIds);

        Map<Long, Integer> mapProduct = new HashMap<>();

        for (OrderItemsDto itemsDto: orderItemsUpdateDto.getOrderItems()) {
            mapProduct.put(itemsDto.getProductId(), itemsDto.getQuantity());
        }


        Double totalAmount = existing.getTotalAmount();

        for (Product product: productList) {
            OrderItems orderItems = new OrderItems();
            orderItems.setOrders(existing);
            orderItems.setProduct(product);
            orderItems.setPrice(product.getPrice());
            orderItems.setQuantity(mapProduct.get(product.getId()));
            orderItems.setSubTotal(orderItems.getPrice() * orderItems.getQuantity());
            totalAmount+= orderItems.getSubTotal();

            if(product.getQuantity() < orderItems.getQuantity()) {
                throw new InputMismatchException("You cannot order this quantity because we have low stock.");
            }
            orderItemsRepository.save(orderItems);
            product.setQuantity(product.getQuantity() - orderItems.getQuantity());
            productRepository.save(product);
        }

        existing.setTotalAmount(totalAmount);
        existing.setOrderItems(null);
        return orderRepository.save(existing);

    }


    public List<Orders> findAll() {
        return this.orderRepository.findAll();
    }



    public Orders findById(Long id) {
        return this.orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order not found"
        ));
    }

    public Orders removeOrderItems(Long id, RemoveOrderItemsDto removeOrderItemsDto) {


        Orders existing = this.findById(id);

        if(existing.getPaymentStatus() == PaymentStatus.PAYED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot add new Items into a payed Order");
        }

        Double totalAmount = existing.getTotalAmount();
        List<OrderItems> orderItemsList = this.orderItemsRepository.findByOrderItemIdIn(removeOrderItemsDto.getOrderItemsId());

        for (OrderItems orderItems: orderItemsList) {
            totalAmount-= orderItems.getSubTotal();
            orderItemsRepository.delete(orderItems);
        }

        existing.setOrderItems(null);
        existing.setTotalAmount(totalAmount);
        return orderRepository.save(existing);

    }
}
