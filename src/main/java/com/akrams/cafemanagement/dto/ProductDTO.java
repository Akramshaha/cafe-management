package com.akrams.cafemanagement.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String productName;
    private Double price;
    private int categoryId;
}
