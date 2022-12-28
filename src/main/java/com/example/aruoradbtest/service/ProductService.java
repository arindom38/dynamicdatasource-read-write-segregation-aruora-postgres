package com.example.aruoradbtest.service;

import com.example.aruoradbtest.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
     List<Product> getAllProductDetails();


    Product createProduct(Product product);
}
