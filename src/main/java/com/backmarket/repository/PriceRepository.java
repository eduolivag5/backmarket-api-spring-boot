package com.backmarket.repository;

import com.backmarket.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {

    List<Price> findByProductId(UUID productId);

    Optional<Price> findByProductIdAndStatus (UUID productId, Integer status);

}
