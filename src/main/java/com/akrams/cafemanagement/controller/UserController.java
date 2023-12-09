package com.akrams.cafemanagement.controller;

import com.akrams.cafemanagement.constants.CodeConstants;
import com.akrams.cafemanagement.rest.UserRest;
import com.akrams.cafemanagement.service.UserService;
import com.akrams.cafemanagement.utils.CodeUtils;
import com.akrams.cafemanagement.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController implements UserRest {

    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }

        return CodeUtils.getResponseEntity(CodeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> reqMap) {
        try {
            return userService.login(reqMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CodeUtils.getResponseEntity(CodeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {

        try {
            return userService.getAllUsers();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
