package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="prices_v2")
public class Price {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "id_product", columnDefinition = "uuid")
    private UUID productId;

    private Integer status;
    private Double price;

    /* Getters y Setters */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getproductId() {
        return productId;
    }

    public void setproductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
