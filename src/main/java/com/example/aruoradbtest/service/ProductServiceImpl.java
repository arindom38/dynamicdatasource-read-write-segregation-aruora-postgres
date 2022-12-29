package com.example.aruoradbtest.service;

import com.example.aruoradbtest.annotation.DataSource;
import com.example.aruoradbtest.config.datasource.DynamicDataSourceContextHolder;
import com.example.aruoradbtest.entity.Product;
import com.example.aruoradbtest.entity.dto.ProductDto;
import com.example.aruoradbtest.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@DataSource
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;
    @Override
    public List<Product> getAllProductDetails() {
        log.info("Product get request received with datasource type {}",DynamicDataSourceContextHolder.getDataSourceType());
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product createProduct(ProductDto dto) {
        log.info("Product create request received with datasource type {} and  dto {}",DynamicDataSourceContextHolder.getDataSourceType(),dto);
        return productRepo.save(convertToEntity(new Product(),dto));
    }

    @Override
    @Transactional
    public Product updateProduct(ProductDto productDto) {
        log.info("Product update request received with datasource type {} and  dto {}",DynamicDataSourceContextHolder.getDataSourceType(),productDto);
        return productRepo.findProductByName(productDto.getName()).map(product ->
            productRepo.save(convertToEntity(product,productDto))
        ).orElse(null);
    }

    private Product convertToEntity(Product product,ProductDto dto){
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDetails(dto.getDetails());
        product.setQuantity(dto.getQuantity());
        return product;
    }
}
