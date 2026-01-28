package com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder @Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
public class RequestCreateProduct {
    @NotBlank(message = "Name cannot be blank")
    String name;
    @NotBlank(message = "Description cannot be blank")
    String description;
    @NotBlank(message = "Prce cannot be blank")
    Integer price;
    @NotBlank(message = "stock cannot be blank")
    Integer stock;
    @NotBlank(message = "categories cannot be blank")
    List<Long> categories;
}
