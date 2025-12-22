package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.repository.BrandRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    /* GET */
    public List<Brand> getBrands(Integer category) {
        if (category != null) {
            List<Brand> brands = brandRepository.findByCategory(category);
            if(brands.isEmpty()) {
                throw new RuntimeException("No se encontro el brand");
            }
            return brands;
        }
        return brandRepository.findAll();
    }

    /* POST */
    public Brand createBrand(Brand brand) {
        brand.setId(null);
        return brandRepository.save(brand);
    }

    /* PUT */
    public void updateBrand(Integer id, Brand brand) {
        Brand existing = brandRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        existing.setMarca(brand.getMarca());
        brandRepository.save(existing);
    }

    /* DELETE */
    public void deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("No existe el brand");
        }
    }

}
