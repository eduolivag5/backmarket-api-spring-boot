package com.backmarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "products_v2")
public class Product {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String createdAt;
    private Integer category;
    private Integer brand;

    @Column(name = "name_short")
    private String nameShort;

    private String name;

    @Column(columnDefinition = "text[]")
    private String[] colors;

    @Column(columnDefinition = "integer[]")
    private Integer[] storages;

    @Column(columnDefinition = "text[]")
    private String[] images;

    @Column(columnDefinition = "text[]")
    private String[] tags;

}
