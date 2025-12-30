package com.backmarket.controller;

import com.backmarket.dto.ApiResponseDto;
import com.backmarket.dto.ProductResponseDto;
import com.backmarket.entity.Product;
import com.backmarket.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name="Products")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /* GET */
    @Operation(
            summary = "Get products",
            description = "Retrieve one or multiple products filtered by id, category, or tags."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getProducts(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tags
    ) {
        if (id != null) {
            return ResponseEntity.ok(new ApiResponseDto<>(
                    false, "OK", productService.getProductById(id)
            ));
        }

        return ResponseEntity.ok(new ApiResponseDto<>(
                false, "OK", productService.getProductsByFilters(category, tags)
        ));
    }

    /* POST */
    @Operation(
            summary = "Create product",
            description = "Create a new product if it does not already exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully."),
            @ApiResponse(responseCode = "409", description = "Product already exists.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createProduct(
            @RequestBody Product product
    ) {
        Optional<Product> existing = productService.getProductByNameOrNameShort(
                product.getName(),
                product.getNameShort()
        );

        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponseDto<>(true, "Product already exists.", null)
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>(false, "OK", productService.createProduct(product))
        );
    }

    /* PUT */
    @Operation(
            summary = "Update product",
            description = "Update an existing product by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @PutMapping
    public ResponseEntity<ApiResponseDto<?>> updateProduct(
            @RequestBody Product product
    ) {
        Optional<ProductResponseDto> existing =
                productService.getProductById(product.getId());

        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponseDto<>(true, "Product does not exist.", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", productService.updateProduct(product))
        );
    }

    /* DELETE */
    @Operation(
            summary = "Delete product",
            description = "Delete a product by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteProduct(
            @RequestParam UUID id
    ) {
        Optional<ProductResponseDto> existing = productService.getProductById(id);

        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponseDto<>(true, "Product does not exist.", null)
            );
        }

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", null)
        );
    }
}
