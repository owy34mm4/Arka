package com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock;

import java.util.List;

import com.arka.product.domain.model.Product;

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

    public static ResponseUpdateStockProduct createFromModel(Product model, String message){
        return ResponseUpdateStockProduct.builder()
            .id(model.getId())
            .name(model.getName().getValue())
            .description(model.getDescription().getValue())
            .stock(model.getStock().getValue())
            .price(model.getPrice().getValue())
            .categories(model.getCategories())
            .message(message)
        .build();
    }

}
