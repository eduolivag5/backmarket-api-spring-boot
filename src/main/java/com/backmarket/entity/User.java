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
@Table(name="users")
public class User {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    private String email;


}
