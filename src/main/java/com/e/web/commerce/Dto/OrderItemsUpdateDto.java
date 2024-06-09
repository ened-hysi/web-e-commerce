package com.e.web.commerce.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class OrderItemsUpdateDto {
    @JsonProperty("order_items")
    private List<OrderItemsDto> orderItems;
}

