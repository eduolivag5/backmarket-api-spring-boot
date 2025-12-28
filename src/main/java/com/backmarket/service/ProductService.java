package com.backmarket.service;

import com.backmarket.dto.PriceResponse;
import com.backmarket.dto.ProductResponse;
import com.backmarket.entity.Product;
import com.backmarket.repository.PriceRepository;
import com.backmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceService priceService;


    /* GET */
    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    public Optional<ProductResponse> getProductById(UUID id){
        return productRepository.findById(id).map(this::mapToProductResponse);
    }

    private ProductResponse mapToProductResponse(Product product){
        List<PriceResponse> prices = priceService.getPricesByProduct(product.getId());

        return new ProductResponse(
                product.getId(),
                product.getCreatedAt(),
                product.getCategory(),
                product.getBrand(),
                product.getNameShort(),
                product.getName(),
                product.getStorages(),
                product.getColors(),
                product.getImages(),
                product.getTags(),
                prices
        );
    }

}
