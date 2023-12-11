package com.akrams.cafemanagement.controller;

import com.akrams.cafemanagement.constants.CodeConstants;
import com.akrams.cafemanagement.dto.CategoryDTO;
import com.akrams.cafemanagement.dto.ProductDTO;
import com.akrams.cafemanagement.rest.UserRest;
import com.akrams.cafemanagement.service.CategoryService;
import com.akrams.cafemanagement.service.ProductService;
import com.akrams.cafemanagement.service.UserService;
import com.akrams.cafemanagement.utils.CodeUtils;
import com.akrams.cafemanagement.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
        List<CategoryDTO> categoryDTOList = userService.getAllCategory();
        if (!categoryDTOList.isEmpty()){
            return ResponseEntity.ok(categoryDTOList);
        }
        return CodeUtils.getResponseEntity("No Categories To Show", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> productDTOList = userService.getAllProduct();
        if (!productDTOList.isEmpty()){
            return ResponseEntity.ok(productDTOList);
        }
        return CodeUtils.getResponseEntity("No Products To Show", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/products/{categoryId}")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable("categoryId") int categoryId){
        List<ProductDTO> productDTOList = userService.getAllProductByCategoryId(categoryId);
        if (!productDTOList.isEmpty()){
            return ResponseEntity.ok(productDTOList);
        }
        return CodeUtils.getResponseEntity("No Products To Show", HttpStatus.BAD_REQUEST);
    }

}
