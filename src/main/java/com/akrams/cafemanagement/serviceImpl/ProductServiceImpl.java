package com.akrams.cafemanagement.serviceImpl;

import com.akrams.cafemanagement.model.Category;
import com.akrams.cafemanagement.model.Product;
import com.akrams.cafemanagement.repository.CategoryRepo;
import com.akrams.cafemanagement.repository.ProductRepo;
import com.akrams.cafemanagement.service.CategoryService;
import com.akrams.cafemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int categoryId) {
        Category category = categoryRepo.findById(categoryId).get();
        return productRepo.findAllByCategory(category);
    }
}
