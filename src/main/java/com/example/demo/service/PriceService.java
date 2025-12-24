package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Price;
import com.example.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    /* GET */
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public Optional<Price> getPriceById(UUID id) {
        return priceRepository.findById(id);
    }

    public List<Price> getPricesByProduct(UUID productId) {
        return priceRepository.findByProductId(productId);
    }

    public Optional<Price> getPriceByProductAndStatus(UUID id, Integer status) {
        return priceRepository.findByProductIdAndStatus(id, status);
    }


    /* POST */
    public Price createPrice(Price price) {
        price.setId(null);
        return priceRepository.save(price);
    }


    /* PUT */
    public Price updatePrice(Price price) {
        Price existing = priceRepository.findById(price.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found."));

        existing.setPrice(price.getPrice());
        existing.setStatus(price.getStatus());
        existing.setproductId(price.getproductId());
        return priceRepository.save(existing);
    }


    /* DELETE */
    public void deletePrice (UUID id) {
        if (!priceRepository.existsById(id)) {
            throw new RuntimeException("Price not found.");
        }
        priceRepository.deleteById(id);
    }

}
