package com.backmarket.service;

import com.backmarket.entity.Brand;
import com.backmarket.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /* GET */
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public List<Brand> getBrandsByCategory (Integer category) {
        List<Brand> brands = brandRepository.findByCategory(category);
        if(brands.isEmpty()) {
            throw new RuntimeException("Brand not found.");
        }
        return brands;
    }

    /* POST */
    public Brand createBrand(Brand brand) {
        brand.setId(null);
        return brandRepository.save(brand);
    }

    /* PUT */
    public Brand updateBrand(Brand brand) {
        Brand existing = brandRepository.findById(brand.getId())
                            .orElseThrow(() -> new RuntimeException("Brand not found."));

        existing.setMarca(brand.getMarca());
        return brandRepository.save(existing);
    }

    /* DELETE */
    public void deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("Brand not found.");
        }
        brandRepository.deleteById(id);
    }

}
