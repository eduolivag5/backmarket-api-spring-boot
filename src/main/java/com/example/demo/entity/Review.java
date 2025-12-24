package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="reviews")
public class Review {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private Double stars;

    @Column(columnDefinition = "text")
    private String comment;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;


    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Double getStars() { return stars; }
    public void setStars(Double stars) { this.stars = stars; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
