package com.akrams.cafemanagement.rest;

import com.akrams.cafemanagement.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String, String> requestMap);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> reqMap);

    @GetMapping("/get")
    public ResponseEntity<List<UserWrapper>> getAllUsers();
}
