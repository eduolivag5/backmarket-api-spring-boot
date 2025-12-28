package com.backmarket.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id", "order", "estado", "description", "screenTags", "caseTags"})
@Setter
@Getter
@Entity
@Table(name="phone_status")
public class PhoneStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;
    private String description;

    @Column(columnDefinition = "text[]")
    private String[] screenTags;

    @Column(columnDefinition = "text[]")
    private String[] caseTags;

    @Column(name = "status_order")
    private Integer order;


}
