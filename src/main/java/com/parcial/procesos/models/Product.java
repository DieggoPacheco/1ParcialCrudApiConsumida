package com.parcial.procesos.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String category;
    @Column(name = "description", length = 10000)
    private String description;
    private String image;

}
