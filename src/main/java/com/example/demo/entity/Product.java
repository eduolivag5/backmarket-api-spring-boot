package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products_v2")
public class Product {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

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

    /* Getter y setter */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public Integer[] getStorages() {
        return storages;
    }

    public void setStorages(Integer[] storages) {
        this.storages = storages;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
