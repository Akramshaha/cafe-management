package com.akrams.cafemanagement.serviceImpl;

import com.akrams.cafemanagement.dto.CategoryDTO;
import com.akrams.cafemanagement.model.Category;
import com.akrams.cafemanagement.repository.CategoryRepo;
import com.akrams.cafemanagement.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category addNewCategory(CategoryDTO categoryDTO) {
        return categoryRepo.save(modelMapper.map(categoryDTO, Category.class));
    }

    @Override
    public Category findCategory(CategoryDTO categoryDTO) {
        return categoryRepo.findByCategoryName(categoryDTO.getCategoryName());
    }
}
