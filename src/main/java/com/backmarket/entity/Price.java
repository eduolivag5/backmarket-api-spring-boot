package com.backmarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name="prices_v2")
public class Price {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "id_product", columnDefinition = "uuid")
    private UUID productId;

    private Integer status;
    private Integer price;

}
