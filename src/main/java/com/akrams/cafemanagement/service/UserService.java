package com.akrams.cafemanagement.service;

import com.akrams.cafemanagement.dto.CategoryDTO;
import com.akrams.cafemanagement.dto.ProductDTO;
import com.akrams.cafemanagement.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> reqMap);

    ResponseEntity<List<UserWrapper>> getAllUsers();

    public List<CategoryDTO> getAllCategory();

    List<ProductDTO> getAllProduct();

    List<ProductDTO> getAllProductByCategoryId(int categoryId);
}
