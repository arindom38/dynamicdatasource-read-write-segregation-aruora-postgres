package com.example.aruoradbtest.service;

import com.example.aruoradbtest.annotation.DataSource;
import com.example.aruoradbtest.entity.Product;
import com.example.aruoradbtest.entity.dto.ProductDto;
import com.example.aruoradbtest.repository.ProductRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@DataSource
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;
    @Override
    public List<Product> getAllProductDetails() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product createProduct(ProductDto dto) {
        return productRepo.save(Product.builder()
                .name(dto.getName())
                .details(dto.getDetails())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build());
    }

    @Override
    @Transactional
    public void updateProduct(ProductDto productDto) {

    }
}
