package com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock;

import java.util.List;

import com.arka.shared.application.ports.out.category.CategoryInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class ResponseUpdateStockProduct {
    Long id;
    String name;
    String description;
    Integer stock;
    Integer price;
    List<CategoryInfo> categories;
    String message;

    public static ResponseUpdateStockProduct create(Long id, String name, String description, Integer price, Integer stock, List<CategoryInfo> categories, String message){
        return ResponseUpdateStockProduct.builder()
            .id(id)
            .name(name)
            .description(description)
            .stock(stock)
            .price(price)
            .categories(categories)
            .message(message)
        .build();
    }
}
