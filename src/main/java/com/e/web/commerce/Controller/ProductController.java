package com.e.web.commerce.Controller;

import com.e.web.commerce.Dto.ProductRequestDto;
import com.e.web.commerce.Entity.Product;
import com.e.web.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto productRequestDto){

        Product response = productService.createProduct(productRequestDto);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts (){
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id) , HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable Long id , @RequestBody ProductRequestDto requestDto ){
        Product response = productService.update(id , requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){
        this.productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
