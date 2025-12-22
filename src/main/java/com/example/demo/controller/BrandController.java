package com.example.demo.controller;

import com.example.demo.entity.Brand;
import com.example.demo.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /* GET */
    @GetMapping
    public List<Brand> getBrands (@RequestParam(required = false) Integer category){
        return brandService.getBrands(category);
    }

    /* POST */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand createBrand(@RequestBody Brand brand){
        return  brandService.createBrand(brand);
    }

    /* PUT */
    @PutMapping
    public void updateBrand (@RequestParam Integer id, @RequestBody Brand brand){
        brandService.updateBrand(id, brand);
    }

    /* DELETE */
    @DeleteMapping
    public void deleteBrand (@RequestParam Integer id){
        brandService.deleteBrand(id);
    }

}
