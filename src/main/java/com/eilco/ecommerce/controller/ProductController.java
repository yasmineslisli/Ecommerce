package com.eilco.ecommerce.controller;

import com.eilco.ecommerce.dto.ProductRequest;
import com.eilco.ecommerce.dto.ProductResponse;
import com.eilco.ecommerce.model.entities.Product;
import com.eilco.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest)
    {
        return ProductResponse.builder().product(productService.save(productRequest)).build();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id){
        return productService.findById(id);
    }
    @GetMapping()
    public List<Product> list(){
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@RequestBody ProductRequest productRequest, @PathVariable("id") Long id){
        return ProductResponse.builder().product(productService.update(productRequest,id)).build();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteById(id);
    }



}
