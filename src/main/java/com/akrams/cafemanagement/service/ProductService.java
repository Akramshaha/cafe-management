package com.akrams.cafemanagement.service;

import com.akrams.cafemanagement.dto.ProductDTO;
import com.akrams.cafemanagement.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();
    public List<ProductDTO> getAllProductsByCategoryId(int categoryId);

    public Product findProductByName(String productName);

    public ProductDTO addNewProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProductsByCategory(int categoryId);
}
