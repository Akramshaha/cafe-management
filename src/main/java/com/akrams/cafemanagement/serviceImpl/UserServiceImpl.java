package com.akrams.cafemanagement.serviceImpl;

import com.akrams.cafemanagement.JWT.CustomUserDetailsService;
import com.akrams.cafemanagement.JWT.JwtUtil;
import com.akrams.cafemanagement.constants.CodeConstants;
import com.akrams.cafemanagement.dto.CategoryDTO;
import com.akrams.cafemanagement.dto.ProductDTO;
import com.akrams.cafemanagement.model.Category;
import com.akrams.cafemanagement.model.User;
import com.akrams.cafemanagement.repository.UserRepository;
import com.akrams.cafemanagement.service.CategoryService;
import com.akrams.cafemanagement.service.ProductService;
import com.akrams.cafemanagement.service.UserService;
import com.akrams.cafemanagement.utils.CodeUtils;
import com.akrams.cafemanagement.wrapper.UserWrapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside SignUp {}", requestMap);

        try {
            if (validateSignUpData(requestMap)){
                Optional<User> user = userRepository.findByEmail(requestMap.get("email"));
                if(!user.isPresent()){
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

    @Override
    public ResponseEntity<String> login(Map<String, String> reqMap) {
        log.info("Inside Login");
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(reqMap.get("email"));
        if (userDetails == null) return CodeUtils.getResponseEntity("User not found", HttpStatus.NOT_FOUND);
        if (!passwordEncoder.matches(reqMap.get("password"), userDetails.getPassword())) return CodeUtils.getResponseEntity("Password not matched", HttpStatus.BAD_REQUEST);
//        log.info(" userDetails.getAuthorities().stream().findAny().get().getAuthority().toUpperCase() {}", userDetails.getAuthorities().stream().findAny().get().getAuthority().toUpperCase());
        return ResponseEntity.ok()
                .header("jwt",(jwtUtil.generate(reqMap.get("email"),
                        userDetails.getAuthorities().stream().findAny().get().getAuthority().toUpperCase())))
                .body("Response with header using ResponseEntity");
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try {
            List<User> userList = userRepository.findAll();
            List<UserWrapper> userWrapperList = userList.stream().map((u) ->modelMapper.map(u, UserWrapper.class)).collect(Collectors.toList());
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
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
        user.setPassword(passwordEncoder.encode(reqMap.get("password")));
        user.setStatus("A");
        user.setRole("USER");

        return user;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> list = categoryService.getAllCategory().stream().map(
                (category) -> modelMapper.map(category,CategoryDTO.class)
        ).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> list = productService.getAllProducts().stream().map(
                (product) -> modelMapper.map(product,ProductDTO.class)
        ).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<ProductDTO> getAllProductByCategoryId(int categoryId) {
        //Category category = categoryService.findCategoryById(categoryId);
        List<ProductDTO> list = productService.getAllProductsByCategoryId(categoryId).stream().map(
                (product) -> modelMapper.map(product,ProductDTO.class)
        ).collect(Collectors.toList());

        return list;
    }
}
