package com.e.web.commerce.service;


import com.e.web.commerce.Dto.OrderDto;
import com.e.web.commerce.Dto.OrderItemsDto;
import com.e.web.commerce.Entity.Orders;
import com.e.web.commerce.Entity.OrderItems;
import com.e.web.commerce.Entity.Product;
import com.e.web.commerce.Enum.OrderStatus;
import com.e.web.commerce.repository.OrderItemsRepository;
import com.e.web.commerce.repository.OrderRepository;
import com.e.web.commerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

            orderItemsRepository.save(orderItems);
        }

        result.setTotalAmount(totalAmount);
        result.setOrderItems(null);
        return orderRepository.save(result);

    }
}
