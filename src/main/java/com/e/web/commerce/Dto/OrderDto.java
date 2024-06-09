package com.e.web.commerce.Dto;

import com.e.web.commerce.Enum.OrderStatus;
import com.e.web.commerce.Enum.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    @JsonProperty("order_status")
    private OrderStatus orderStatus;


    @JsonProperty("order_items")
    private List<OrderItemsDto> orderItems;
}
