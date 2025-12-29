package com.backmarket.controller;

import com.backmarket.dto.ApiResponse;
import com.backmarket.dto.ProductResponse;
import com.backmarket.entity.Product;
import com.backmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /* GET */
    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getProducts (
        @RequestParam(required = false) UUID id,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String tags
    ) {
        if (id != null) {
            return ResponseEntity.ok(new ApiResponse<>(
                    false, "ok", productService.getProductById(id)
            ));
        }

        return ResponseEntity.ok(new ApiResponse<>(
                false, "ok", productService.getProductsByFilters(category, tags)
        ));
    }


    /* POST */
    @PostMapping()
    public ResponseEntity<ApiResponse<?>> createProduct (
            @RequestBody Product product
    ) {
        Optional<Product> existing = productService.getProductByNameOrNameShort(
                product.getName(),
                product.getNameShort()
        );
        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(
                    true, "The product already exists.", null
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                false, "ok",  productService.createProduct(product)
        ));
    }


    /* PUT */
    @PutMapping()
    public ResponseEntity<ApiResponse<?>> updateProduct (
            @RequestBody Product product
    ) {
        Optional<ProductResponse> existing = productService.getProductById(product.getId());

        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(
                    true, "The product not exists.", null
            ));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                false, "ok",  productService.updateProduct(product)
        ));
    }


    /* DELETE */
    @DeleteMapping()
    public ResponseEntity<ApiResponse<?>> deleteProduct (
            @RequestParam UUID id
    ) {
        Optional<ProductResponse> existing = productService.getProductById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(
                    true, "The product not exists.", null
            ));
        }
        productService.deleteProduct(id);
        return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                false, "ok", null
        ));
    }
}
