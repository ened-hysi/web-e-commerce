package com.e.web.commerce.Dto;

import com.e.web.commerce.Enum.OrderStatus;
import com.e.web.commerce.Enum.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UpdateOrderDto {



    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;
    @JsonProperty("order_status")
    private OrderStatus orderStatus;
}
