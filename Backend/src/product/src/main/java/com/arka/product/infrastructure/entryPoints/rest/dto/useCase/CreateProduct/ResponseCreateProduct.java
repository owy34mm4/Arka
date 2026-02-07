package com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct;

import java.util.List;

import com.arka.product.domain.model.Product;
import com.arka.shared.application.ports.out.category.CategoryInfo;

import lombok.Builder;
import lombok.Getter;


@Builder @Getter 
public class ResponseCreateProduct {
    Long id;
    String name;
    String description;
    Integer stock;
    String currency;
    Integer price;
    List<CategoryInfo> categories;
    String message;

    public static ResponseCreateProduct createFromModel(Product model, String message){
        return ResponseCreateProduct.builder()
            .id(model.getId())
            .name(model.getName().getValue())
            .description(model.getDescription().getValue())
            .stock(model.getStock().getValue())
            .price(model.getPrice().getValue())
            .currency(model.getPrice().getCurrency())
            .categories(model.getCategories())
            .message(message)
        .build();
    }
}
