package com.backmarket.controller;

import com.backmarket.dto.ApiResponse;
import com.backmarket.entity.Brand;
import com.backmarket.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /* GET */
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getBrands(
            @RequestParam(required = false) Integer category) {

        if (category != null) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "ok",
                            brandService.getBrandsByCategory(category))
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(false, "ok", brandService.getAllBrands())
        );
    }

    /* POST */
    @PostMapping
    public ResponseEntity<ApiResponse<Brand>> createBrand(
            @RequestBody Brand brand) {

        Brand saveBrand = brandService.createBrand(brand);
        return ResponseEntity.status(201)
                .body(new ApiResponse<>(false, "ok", saveBrand));
    }

    /* PUT */
    @PutMapping
    public ResponseEntity<ApiResponse<Brand>> updateBrand(
            @RequestBody Brand brand) {

        Brand saveBrand = brandService.updateBrand(brand);
        return ResponseEntity.ok(
                new ApiResponse<>(false, "ok", saveBrand)
        );
    }

    /* DELETE */
    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteBrand(
            @RequestParam Integer id) {

        brandService.deleteBrand(id);
        return ResponseEntity.ok(
                new ApiResponse<>(false, "ok", null)
        );
    }
}

