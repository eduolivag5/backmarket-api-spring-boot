package com.backmarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductResponseDto {

    private UUID id;
    private String createdAt;
    private Integer category;
    private Integer brand;

    private String nameShort;
    private String name;

    private String[] colors;
    private Integer[] storages;
    private String[] images;
    private String[] tags;

    private List<PriceResponseDto> prices;

    /* Constructor */
    public ProductResponseDto(UUID id, String createdAt, Integer category, Integer brand, String nameShort, String name, Integer[] storages, String[] colors, String[] images, String[] tags, List<PriceResponseDto> prices) {
        this.id = id;
        this.createdAt = createdAt;
        this.category = category;
        this.brand = brand;
        this.nameShort = nameShort;
        this.name = name;
        this.storages = storages;
        this.colors = colors;
        this.images = images;
        this.tags = tags;
        this.prices = prices;
    }
}
