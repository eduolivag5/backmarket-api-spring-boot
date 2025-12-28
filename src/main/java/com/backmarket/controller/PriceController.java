package com.backmarket.controller;

import com.backmarket.dto.ApiResponse;
import com.backmarket.dto.PriceResponse;
import com.backmarket.entity.Price;
import com.backmarket.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    /* GET */
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPrices (@RequestParam(required = false) UUID id) {
        if (id != null) {
            List<PriceResponse> pricesList = priceService.getPricesByProduct(id);
            if (pricesList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                        true, "Product prices not found.", null
                ));
            }
            return ResponseEntity.ok(new ApiResponse<>(
                    false, "ok", pricesList
            ));
        }
        return ResponseEntity.ok(new ApiResponse<>(
                false, "ok", priceService.getAllPrices()
        ));
    }


    /* POST */
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPrice (@RequestBody(required = false) Price price) {
        if (price != null) {
            Optional<Price> existing = priceService.getPriceByProductAndStatus(price.getId(), price.getStatus());
            if (existing.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(
                        true, "Price already exist.", null
                ));
            }
            return ResponseEntity.ok(new ApiResponse<>(
                    false, "ok",  priceService.createPrice(price)
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                true, "Bad Request.", null
        ));
    }


    /* PUT */
    @PutMapping
    public ResponseEntity<ApiResponse<?>> updatePrice (@RequestBody(required = false) Price price) {
        if (price != null) {
            Optional<PriceResponse> existing = priceService.getPriceById(price.getId());
            if (existing.isPresent()) {
                return ResponseEntity.ok(new ApiResponse<>(
                        false, "ok", priceService.updatePrice(price)
                ));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    true, "Product price not found.", null
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                true, "Bad request.", null
        ));
    }


    /* DELETE */
    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deletePrice (@RequestParam(required = false) UUID id) {
        if (id != null) {
            Optional<PriceResponse> existing = priceService.getPriceById(id);
            if (existing.isPresent()) {
                priceService.deletePrice(id);
                return ResponseEntity.ok(new ApiResponse<>(
                        false, "ok", null
                ));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    true, "Product price not found.", null
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                true, "Bad request.", null
        ));
    }

}
