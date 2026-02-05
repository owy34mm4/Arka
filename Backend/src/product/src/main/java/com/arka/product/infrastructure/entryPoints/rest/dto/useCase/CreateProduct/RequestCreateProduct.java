package com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestCreateProduct {
    Long requester_id;
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
