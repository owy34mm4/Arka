package com.arka.product.application.mapper.adapter;


import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.shared.application.mapper.gateway.ILayerMapper;

import lombok.Builder;


@Builder
public class CreateProductLayerMapper implements ILayerMapper<Product , CreateProductCommand > {

    @Override
    public Product toDomain(CreateProductCommand cmd) {
        return Product.builder()
            .id(null)
            .name(cmd.getName())
            .description(cmd.getDescription())
            .price(cmd.getPrice())
            .stock(cmd.getStock())
            .categoriesIds(cmd.getCategories())
            //.shoppingCartsIds(null)
            //.ordersIds(null)
        .build();
        
    }

    @Override
    public CreateProductCommand toCommand(Product p) {
        return CreateProductCommand.builder()
            .name(p.getName())
            .description(p.getDescription())
            .price(p.getPrice())
            .stock(p.getStock())
            .categories(p.getCategoriesIds())
        .build();
    }
    
}
