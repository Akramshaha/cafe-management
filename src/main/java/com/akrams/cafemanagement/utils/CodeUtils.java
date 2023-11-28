package com.akrams.cafemanagement.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CodeUtils {

    private CodeUtils() {
    }

    public static ResponseEntity<String> getResponseEntity(String responseMsg, HttpStatus code){
        return new ResponseEntity<String>("{\"message\":\""+responseMsg+"\"}", code);
    }
}
