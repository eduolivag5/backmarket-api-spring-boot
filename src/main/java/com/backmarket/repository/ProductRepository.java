package com.backmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backmarket.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}

