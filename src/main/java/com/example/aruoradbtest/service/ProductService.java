package com.example.aruoradbtest.service;

import com.example.aruoradbtest.entity.Product;
import com.example.aruoradbtest.entity.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
     List<Product> getAllProductDetails();


    Product createProduct(ProductDto productDto);

    void updateProduct(ProductDto productDto);
}
