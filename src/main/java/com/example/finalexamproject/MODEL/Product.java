package com.example.finalexamproject.MODEL;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "price")
    private double price;

    @Column(name= "weight")
    private double weight;

    @Column(name = "description")
    private String description;

    @Column(name = "imageName")
    private String imageName;

}
