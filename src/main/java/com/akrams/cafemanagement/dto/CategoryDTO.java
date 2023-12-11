package com.akrams.cafemanagement.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoryDTO {
    private int id;
    private String categoryName;
    private Timestamp createdDateTime;
}
