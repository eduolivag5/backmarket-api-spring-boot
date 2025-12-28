package com.backmarket.service;

import com.backmarket.dto.PriceResponse;
import com.backmarket.entity.PhoneStatus;
import com.backmarket.entity.Price;
import com.backmarket.repository.PhoneStatusRepository;
import com.backmarket.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PhoneStatusRepository  phoneStatusRepository;

    /* GET */
    public List<PriceResponse> getAllPrices() {
        return priceRepository.findAll().stream().map(this::mapToPriceDto).toList();
    }

    public Optional<PriceResponse> getPriceById(UUID id) {
        return priceRepository.findById(id).map(this::mapToPriceDto);
    }

    public List<PriceResponse> getPricesByProduct(UUID productId) {
        return priceRepository.findByProductId(productId).stream().map(this::mapToPriceDto).toList();
    }

    public Optional<Price> getPriceByProductAndStatus(UUID id, Integer status) {
        return priceRepository.findByProductIdAndStatus(id, status);
    }


    /* POST */
    public PriceResponse createPrice(Price price) {
        price.setId(null);
        Price savedPrice = priceRepository.save(price);
        return mapToPriceDto(savedPrice);
    }


    /* PUT */
    public PriceResponse updatePrice(Price price) {
        Price existing = priceRepository.findById(price.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found."));

        existing.setPrice(price.getPrice());
        existing.setStatus(price.getStatus());
        existing.setProductId(price.getProductId());
        Price savedPrice = priceRepository.save(existing);
        return mapToPriceDto(savedPrice);
    }


    /* DELETE */
    public void deletePrice (UUID id) {
        if (!priceRepository.existsById(id)) {
            throw new RuntimeException("Price not found.");
        }
        priceRepository.deleteById(id);
    }


    /* Mapper */
    private PriceResponse mapToPriceDto (Price price){
        PhoneStatus phoneStatus = phoneStatusRepository.findById(price.getStatus())
                .orElseThrow(() -> new RuntimeException("Phone status not found."));

        return new PriceResponse(
                price.getStatus(),
                phoneStatus.getEstado(),
                price.getPrice()
        );
    }

}
