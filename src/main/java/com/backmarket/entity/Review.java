package com.backmarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
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


}
