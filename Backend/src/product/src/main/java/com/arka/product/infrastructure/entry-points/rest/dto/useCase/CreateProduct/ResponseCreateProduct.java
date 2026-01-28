package com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct;

import java.util.List;

import com.arka.shared.application.ports.out.category.CategoryInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class ResponseCreateProduct {
    Long id;
    String name;
    String description;
    Integer price;
    List<CategoryInfo> categories;
    String message;

    public static ResponseCreateProduct create(Long id, String name, String description, Integer price, List<CategoryInfo> categories, String message){
        return ResponseCreateProduct.builder()
            .id(id)
            .name(name)
            .description(description)
            .price(price)
            .categories(categories)
            .message(message)
        .build();
    }
}
