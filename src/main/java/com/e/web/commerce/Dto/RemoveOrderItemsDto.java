package com.e.web.commerce.Dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RemoveOrderItemsDto {


    @JsonProperty("order_items_id")
    List<Long> orderItemsId = new ArrayList<>();

}
