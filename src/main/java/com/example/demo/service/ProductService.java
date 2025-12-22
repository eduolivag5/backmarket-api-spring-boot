package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getFirstProduct() {
        return productRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() ->
                        new RuntimeException("No hay productos en la base de datos"));
    }
}
