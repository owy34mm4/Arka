package com.arka.product.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.product.infrastructure.persistence.entity.ProductTable;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;

import lombok.Builder;


@Component
@Builder
public class PersistanceProductMapper implements IPersistanceMapper < com.arka.product.domain.model.Product , ProductTable > {

    public ProductTable toEntity(com.arka.product.domain.model.Product p){
        if (p==null) return null;
        return com.arka.product.infrastructure.persistence.entity.ProductTable.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stock(p.getStock())
                .categoriesId(p.getCategoriesIds())
            .build();
    }

    public  com.arka.product.domain.model.Product toDomain (ProductTable p){

        if (p==null) return null;

        return com.arka.product.domain.model.Product.builder()
        .id(p.getId())
        .name(p.getName())
        .description(p.getDescription())
        .price(p.getPrice())
        .stock(p.getStock())
        .categoriesIds(p.getCategoriesId())
        .shopingCartsIds(null)
        .ordersIds(null)
        .build();

    }
}
