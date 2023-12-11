package com.akrams.cafemanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Double price;
    @OneToOne(cascade = CascadeType.DETACH)
    private Category category;
}
