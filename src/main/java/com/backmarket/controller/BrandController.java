package com.backmarket.controller;

import com.backmarket.dto.ApiResponseDto;
import com.backmarket.entity.Brand;
import com.backmarket.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Brands")
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /* GET */
    @Operation(
            summary = "Get brands",
            description = "Retrieve all brands or filter them by category."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getBrands(
            @RequestParam(required = false) Integer category
    ) {

        if (category != null) {
            return ResponseEntity.ok(
                    new ApiResponseDto<>(false, "OK",
                            brandService.getBrandsByCategory(category))
            );
        }

        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", brandService.getAllBrands())
        );
    }

    /* POST */
    @Operation(
            summary = "Create brand",
            description = "Create a new brand."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Brand>> createBrand(
            @RequestBody Brand brand
    ) {

        Brand savedBrand = brandService.createBrand(brand);
        return ResponseEntity.status(201)
                .body(new ApiResponseDto<>(false, "OK", savedBrand));
    }

    /* PUT */
    @Operation(
            summary = "Update brand",
            description = "Update an existing brand."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand updated successfully."),
            @ApiResponse(responseCode = "404", description = "Brand not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @PutMapping
    public ResponseEntity<ApiResponseDto<Brand>> updateBrand(
            @RequestBody Brand brand
    ) {

        Brand savedBrand = brandService.updateBrand(brand);
        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", savedBrand)
        );
    }

    /* DELETE */
    @Operation(
            summary = "Delete brand",
            description = "Delete a brand by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Brand not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteBrand(
            @RequestParam Integer id
    ) {

        brandService.deleteBrand(id);
        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", null)
        );
    }
}
