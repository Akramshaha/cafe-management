package com.akrams.cafemanagement.serviceImpl;

import com.akrams.cafemanagement.dto.ProductDTO;
import com.akrams.cafemanagement.model.Category;
import com.akrams.cafemanagement.model.Product;
import com.akrams.cafemanagement.repository.CategoryRepo;
import com.akrams.cafemanagement.repository.ProductRepo;
import com.akrams.cafemanagement.service.CategoryService;
import com.akrams.cafemanagement.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<ProductDTO> getAllProductsByCategoryId(int categoryId) {
        Category category = categoryRepo.findById(categoryId).get();
        List<ProductDTO> productDTOList = productRepo.findAllByCategory(category).stream().map(
                (p) -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public Product findProductByName(String productName) {
        return productRepo.findByProductName(productName);
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Category category = categoryRepo.findById(productDTO.getCategoryId()).get();
        product.setCategory(category);
        return modelMapper.map(productRepo.save(product),ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(int categoryId) {
        return null;
    }
}
