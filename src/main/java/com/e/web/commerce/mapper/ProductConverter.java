package com.e.web.commerce.mapper;

import com.e.web.commerce.Dto.ProductRequestDto;
import com.e.web.commerce.Entity.Category;
import com.e.web.commerce.Entity.Product;

public class ProductConverter {
    public static Product convertToEntity(ProductRequestDto requestDto){
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());
        product.setCategory(new Category(requestDto.getCategory()));




        return product;
    }
}
