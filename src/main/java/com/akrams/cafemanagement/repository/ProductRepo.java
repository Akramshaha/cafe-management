package com.akrams.cafemanagement.repository;

import com.akrams.cafemanagement.model.Category;
import com.akrams.cafemanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategory(Category category);
}
