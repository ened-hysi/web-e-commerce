package com.e.web.commerce.Dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProductRequestDto {
    private String name;

    private String description;

    private Integer quantity;

    private Double price;

    private Long category;
}
