package com.akrams.cafemanagement.serviceImpl;

import com.akrams.cafemanagement.constants.CodeConstants;
import com.akrams.cafemanagement.model.User;
import com.akrams.cafemanagement.repository.UserRepository;
import com.akrams.cafemanagement.service.UserService;
import com.akrams.cafemanagement.utils.CodeUtils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside SignUp {}", requestMap);

        try {
            if (validateSignUpData(requestMap)){
                User user = userRepository.findByEmail(requestMap.get("email"));
                if(Objects.isNull(user)){
                    userRepository.save(getUserFromMap(requestMap));
                    return CodeUtils.getResponseEntity(CodeConstants.SUCCESSFULLY_REGISTER,HttpStatus.CREATED);
                }else{
                    return CodeUtils.getResponseEntity(CodeConstants.USER_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            }else {
                return CodeUtils.getResponseEntity(CodeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CodeUtils.getResponseEntity(CodeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpData(Map<String, String> reqMap){
        if(reqMap.containsKey("name") && reqMap.containsKey("contactNumber")
                && reqMap.containsKey("email") && reqMap.containsKey("password")){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> reqMap){
        User user = new User();
        user.setName(reqMap.get("name"));
        user.setEmail(reqMap.get("email"));
        user.setContactNumber(reqMap.get("contactNumber"));
        user.setPassword(reqMap.get("password"));
        user.setStatus("A");
        user.setRole("USER");

        return user;
    }
}
