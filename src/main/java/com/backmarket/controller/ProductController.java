package com.backmarket.controller;

import com.backmarket.dto.ApiResponse;
import com.backmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getProducts (
        @RequestParam(required = false) UUID id,
        @RequestParam(required = false) Integer category,
        @RequestParam(required = false) String tags
    ) {
        if (id != null) {
            return ResponseEntity.ok(new ApiResponse<>(
                    false, "ok", productService.getProductById(id)
            ));
        }

        return ResponseEntity.ok(new ApiResponse<>(
                false, "ok", productService.getAllProducts()
        ));

    }
}
