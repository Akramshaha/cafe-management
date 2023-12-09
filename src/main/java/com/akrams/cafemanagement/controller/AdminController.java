package com.akrams.cafemanagement.controller;

import com.akrams.cafemanagement.constants.CodeConstants;
import com.akrams.cafemanagement.dto.CategoryDTO;
import com.akrams.cafemanagement.model.Category;
import com.akrams.cafemanagement.service.CategoryService;
import com.akrams.cafemanagement.utils.CodeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/category/add")
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryDTO categoryDTO){
        if(categoryDTO != null){
            Category category = categoryService.findCategory(categoryDTO);
            if(category != null){
                return CodeUtils.getResponseEntity("Category Already Exists", HttpStatus.BAD_REQUEST);
            }
            CategoryDTO category1 = modelMapper.map(categoryService.addNewCategory(categoryDTO), CategoryDTO.class);
            return CodeUtils.getResponseEntityObj(category1,HttpStatus.CREATED );
        }
        return CodeUtils.getResponseEntity(CodeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
