package com.backmarket.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id", "marca", "category", "imgHeader"})
@Setter
@Getter
@Entity
@Table(name="brands_v2")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;

    private Integer category;

    private String imgHeader;

}
