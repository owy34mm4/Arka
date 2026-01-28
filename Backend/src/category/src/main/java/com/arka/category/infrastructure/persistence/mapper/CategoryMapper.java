package com.arka.category.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.arka.category.infrastructure.persistence.entity.Category;


import lombok.Builder;

@Builder
@Component
public class CategoryMapper {

    
    public com.arka.category.domain.model.Category toDomain(Category c){
        if (c==null) return null;
        return com.arka.category.domain.model.Category.builder()
        .id(c.getId())
        .name(c.getName())
        .productsId(c.getProducts())
        .build();
    }

    public Category toEntity ( com.arka.category.domain.model.Category c){
        if (c==null) return null;
        return Category.builder()
            .id(c.getId())
            .name(c.getName())
            .products(c.getProductsId())
            .build();
    }

    
}
