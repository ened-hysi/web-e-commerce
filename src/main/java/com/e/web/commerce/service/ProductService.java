package com.e.web.commerce.service;

import com.e.web.commerce.Dto.ProductRequestDto;
import com.e.web.commerce.Entity.Category;
import com.e.web.commerce.Entity.Product;
import com.e.web.commerce.repository.ProductRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Product createProduct(ProductRequestDto requestDto){
         Product product = modelMapper.map(requestDto, Product.class);
         product.setCategory(new Category(requestDto.getCategory()));
        return productRepository.save(product);

    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }


    public Product findById(Long id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get();
        }
            return null;
    }

    public Product update(Long id, ProductRequestDto requestDto){
        Product existing = this.findById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(requestDto , existing);

        return productRepository.save(existing);

        }

    public void delete(Long id){
        Product product = this.findById(id);
        this.productRepository.delete(product);
    }




}
