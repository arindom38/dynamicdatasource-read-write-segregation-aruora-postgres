package com.example.aruoradbtest.entity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {

    String name;

    String details;

    Double price;

    Integer quantity;
}
