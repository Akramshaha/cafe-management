package com.akrams.cafemanagement.service;

import com.akrams.cafemanagement.dto.CategoryDTO;
import com.akrams.cafemanagement.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategory();
    public Category addNewCategory(CategoryDTO categoryDTO);

    public Category findCategory(CategoryDTO categoryDTO);

}
