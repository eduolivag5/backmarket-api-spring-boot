package com.backmarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ReviewResponseDto {

    private UUID id;
    private Double stars;
    private String comment;
    private String image;
    private UUID productId;
    private String productNameShort;
    private String userName;

    // Constructor
    public ReviewResponseDto(UUID id, Double stars, String comment, String image,
                             UUID productId, String productNameShort, String userName) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.image = image;
        this.productId = productId;
        this.productNameShort = productNameShort;
        this.userName = userName;
    }

}
