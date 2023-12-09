package com.akrams.cafemanagement.service;

import com.akrams.cafemanagement.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();
    public List<Product> getAllProductsByCategoryId(int categoryId);
}
