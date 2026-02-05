package com.arka.product.application.useCase.command;

import java.util.List;

import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.RequestCreateProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateProductCommand {
    
    Long requesterId;
    Long id;
    String name;
    String description;
    Integer price;  
    Integer stock;
    
    List<Long> categories;

    public static CreateProductCommand createFromRequest(RequestCreateProduct request){
        return CreateProductCommand.builder()
            .requesterId(request.getRequester_id())
            .id(null)
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getStock())
            .categories(request.getCategories())
        .build();
    }

    public Product toModel(){
        return Product.builder()
        .id(this.id)
        .name(this.name)
        .description(this.description)
        .price(this.price)
        .stock(this.stock)
        .categoriesIds(this.categories)
        .build();
    }
}
