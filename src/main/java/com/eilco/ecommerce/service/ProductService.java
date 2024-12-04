package com.eilco.ecommerce.service;

import com.eilco.ecommerce.dto.ProductRequest;
import com.eilco.ecommerce.model.entities.Product;
import com.eilco.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    @Autowired
    private final ProductRepository repository;


    public Product save(ProductRequest productRequest){
        return repository.save(convertProductRequestToProduct(productRequest, null));
    }

    public Product update(ProductRequest productRequest, Long id){
        return repository.save(convertProductRequestToProduct(productRequest, id));
    }

    public List<Product> findAll(){
        return (List<Product>) repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Optional<Product> findById(Long id){
        return repository.findById(id);
    }

    private Product convertProductRequestToProduct(ProductRequest productRequest, Long id){
        return Product.builder()
                .id(id)
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .imageurl(productRequest.getImageurl())
                .quantity(productRequest.getQuantity())
                .description(productRequest.getDescription())
                .active(productRequest.isActive())
                .category(productRequest.getCategory())
                .build();
    }
}
