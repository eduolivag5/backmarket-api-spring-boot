package com.backmarket.controller;

import com.backmarket.dto.ApiResponseDto;
import com.backmarket.dto.PriceResponseDto;
import com.backmarket.entity.Price;
import com.backmarket.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name="Prices")
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    /* GET */
    @Operation(
            summary = "Get prices",
            description = "Retrieve all prices or prices associated with a specific product."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully."),
            @ApiResponse(responseCode = "404", description = "Product prices not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getPrices(
            @RequestParam(required = false) UUID id
    ) {
        if (id != null) {
            List<PriceResponseDto> pricesList = priceService.getPricesByProduct(id);

            if (pricesList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponseDto<>(true, "Product prices not found.", null)
                );
            }

            return ResponseEntity.ok(
                    new ApiResponseDto<>(false, "OK", pricesList)
            );
        }

        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", priceService.getAllPrices())
        );
    }

    /* POST */
    @Operation(
            summary = "Create price",
            description = "Create a new price for a product and status combination."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price created successfully."),
            @ApiResponse(responseCode = "409", description = "Price already exists.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createPrice(
            @RequestBody(required = false) Price price
    ) {
        if (price != null) {
            Optional<Price> existing =
                    priceService.getPriceByProductAndStatus(price.getId(), price.getStatus());

            if (existing.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ApiResponseDto<>(true, "Price already exists.", null)
                );
            }

            return ResponseEntity.ok(
                    new ApiResponseDto<>(false, "OK", priceService.createPrice(price))
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponseDto<>(true, "Bad request.", null)
        );
    }

    /* PUT */
    @Operation(
            summary = "Update price",
            description = "Update an existing product price."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price updated successfully."),
            @ApiResponse(responseCode = "404", description = "Product price not found.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @PutMapping
    public ResponseEntity<ApiResponseDto<?>> updatePrice(
            @RequestBody(required = false) Price price
    ) {
        if (price != null) {
            Optional<PriceResponseDto> existing =
                    priceService.getPriceById(price.getId());

            if (existing.isPresent()) {
                return ResponseEntity.ok(
                        new ApiResponseDto<>(false, "OK",
                                priceService.updatePrice(price))
                );
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(true, "Product price not found.", null)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponseDto<>(true, "Bad request.", null)
        );
    }

    /* DELETE */
    @Operation(
            summary = "Delete price",
            description = "Delete a product price by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Product price not found.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request parameter.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deletePrice(
            @RequestParam(required = false) UUID id
    ) {
        if (id != null) {
            Optional<PriceResponseDto> existing =
                    priceService.getPriceById(id);

            if (existing.isPresent()) {
                priceService.deletePrice(id);

                return ResponseEntity.ok(
                        new ApiResponseDto<>(false, "OK", null)
                );
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(true, "Product price not found.", null)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponseDto<>(true, "Bad request.", null)
        );
    }
}
