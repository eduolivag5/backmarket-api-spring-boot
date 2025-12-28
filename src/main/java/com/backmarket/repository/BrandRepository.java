package com.backmarket.repository;

import com.backmarket.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findByCategory(Integer category);

}
