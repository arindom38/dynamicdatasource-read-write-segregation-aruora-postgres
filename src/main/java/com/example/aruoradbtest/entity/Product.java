package com.example.aruoradbtest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Product {

    @Id
    Long id;

    String name;

    String details;

    Double price;

    Integer quantity;

    LocalDateTime createdAt = LocalDateTime.now();

    LocalDateTime modifiedAt = LocalDateTime.now();

}
