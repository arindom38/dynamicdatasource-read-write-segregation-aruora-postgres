package com.example.aruoradbtest.controller;

import com.example.aruoradbtest.entity.dto.ProductDto;
import com.example.aruoradbtest.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productService.getAllProductDetails());
    }
    @PostMapping("/products")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PutMapping("/products/update")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }
}
