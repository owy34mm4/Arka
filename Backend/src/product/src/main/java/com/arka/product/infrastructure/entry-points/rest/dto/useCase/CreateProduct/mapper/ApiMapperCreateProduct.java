package com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct.mapper;

import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct.RequestCreateProduct;
import com.arka.shared.infrastructure.api.mapper.gateway.IApiMapper;

import lombok.Builder;

@Builder
public class ApiMapperCreateProduct implements IApiMapper< CreateProductCommand , RequestCreateProduct  > {
    public CreateProductCommand toCommand(RequestCreateProduct p){
        return CreateProductCommand.builder()
            .name(p.getName())
            .description(p.getDescription())
            .price(p.getPrice())
            .stock(p.getStock())
            .categories(p.getCategories())
        .build();
    }

    
    public Product toDomain(CreateProductCommand cmd){
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

}
