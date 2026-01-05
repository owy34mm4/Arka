package com.arka.category.infrastructure.persistence.mapper;

import com.arka.category.infrastructure.persistence.entity.Category;
import com.arka.product.infrastructure.persistence.mapper.ProductMapper;

public class CategoryMapper {

    
    public static  com.arka.category.domain.model.Category toDomain(Category c){
        if (c==null) return null;
        return com.arka.category.domain.model.Category.builder()
        .id(c.getId())
        .name(c.getName())
        .products(c.getProducts().stream().map(
            p ->ProductMapper.toDomain(p)
            )
        .toList())
        .build();
    }

    public static Category toEntity ( com.arka.category.domain.model.Category c){
        if (c==null) return null;
        return Category.builder()
            .id(c.getId())
            .name(c.getName())
            .products(c.getProducts().stream().map(
                p-> ProductMapper.toEntity(p)
            ).toList())
            .build();
    }

    
}
