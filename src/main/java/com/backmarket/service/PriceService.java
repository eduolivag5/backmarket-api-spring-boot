package com.backmarket.service;

import com.backmarket.dto.PriceResponseDto;
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
    public List<PriceResponseDto> getAllPrices() {
        return priceRepository.findAll().stream().map(this::mapToPriceDto).toList();
    }

    public Optional<PriceResponseDto> getPriceById(UUID id) {
        return priceRepository.findById(id).map(this::mapToPriceDto);
    }

    public List<PriceResponseDto> getPricesByProduct(UUID productId) {
        return priceRepository.findByProductId(productId).stream().map(this::mapToPriceDto).toList();
    }

    public Optional<Price> getPriceByProductAndStatus(UUID id, Integer status) {
        return priceRepository.findByProductIdAndStatus(id, status);
    }


    /* POST */
    public PriceResponseDto createPrice(Price price) {
        price.setId(null);
        Price savedPrice = priceRepository.save(price);
        return mapToPriceDto(savedPrice);
    }


    /* PUT */
    public PriceResponseDto updatePrice(Price price) {
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
    private PriceResponseDto mapToPriceDto (Price price){
        PhoneStatus phoneStatus = phoneStatusRepository.findById(price.getStatus())
                .orElseThrow(() -> new RuntimeException("Phone status not found."));

        return new PriceResponseDto(
                price.getStatus(),
                phoneStatus.getEstado(),
                price.getPrice()
        );
    }

}
