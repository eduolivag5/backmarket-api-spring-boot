package com.example.demo.dto;

import java.util.UUID;

public class ReviewResponse {

    private UUID id;
    private Double stars;
    private String comment;
    private String image;
    private UUID productId;
    private String productNameShort;
    private String userName;

    // Constructor que coincide con la query JPQL
    public ReviewResponse(UUID id, Double stars, String comment, String image,
                          UUID productId, String productNameShort, String userName) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.image = image;
        this.productId = productId;
        this.productNameShort = productNameShort;
        this.userName = userName;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Double getStars() { return stars; }
    public void setStars(Double stars) { this.stars = stars; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }

    public String getProductNameShort() { return productNameShort; }
    public void setProductNameShort(String productNameShort) { this.productNameShort = productNameShort; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
