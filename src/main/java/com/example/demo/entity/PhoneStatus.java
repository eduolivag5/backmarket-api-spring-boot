package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="phone_status")
public class PhoneStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;
    private String description;

    @Column(columnDefinition = "text[]")
    private String screenTags;

    @Column(columnDefinition = "text[]")
    private String caseTags;


    /* Getters y Setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScreenTags() {
        return screenTags;
    }

    public void setScreenTags(String screenTags) {
        this.screenTags = screenTags;
    }

    public String getCaseTags() {
        return caseTags;
    }

    public void setCaseTags(String caseTags) {
        this.caseTags = caseTags;
    }
}
