package com.e.web.commerce.Controller;

import com.e.web.commerce.Dto.OrderDto;
import com.e.web.commerce.Dto.OrderItemsUpdateDto;
import com.e.web.commerce.Dto.RemoveOrderItemsDto;
import com.e.web.commerce.Dto.UpdateOrderDto;
import com.e.web.commerce.Entity.Orders;
import com.e.web.commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> create (@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }


    @PatchMapping("{id}")
    public ResponseEntity<Orders> updateOrder(@RequestBody UpdateOrderDto updateOrderDto, @PathVariable Long id) {
        return new ResponseEntity<>(orderService.updateOrder(id, updateOrderDto), HttpStatus.OK);

    }


    @PostMapping("add_times/{id}")
    public ResponseEntity<Orders> addOrderItems(@RequestBody OrderItemsUpdateDto orderItemsUpdateDto, @PathVariable Long id) {
        return new ResponseEntity<>(orderService.addOrderItems(id, orderItemsUpdateDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Orders> removeOrderItems(@PathVariable Long id, @RequestBody RemoveOrderItemsDto removeOrderItemsDto) {
        return new ResponseEntity<>(orderService.removeOrderItems(id, removeOrderItemsDto), HttpStatus.OK);

    }
}
