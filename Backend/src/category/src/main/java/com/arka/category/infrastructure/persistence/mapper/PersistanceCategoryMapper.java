package com.arka.category.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.arka.category.domain.model.Category;
import com.arka.category.domain.valueObject.CategoryName;
import com.arka.category.infrastructure.persistence.entity.CategoryTable;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;

import lombok.Builder;

@Builder
@Component
public class PersistanceCategoryMapper implements IPersistanceMapper<Category, CategoryTable> {

    
    public Category toDomain(CategoryTable c){
        if (c==null) return null;
        return Category.builder()
        .id(c.getId())
        .name(CategoryName.create(c.getName()))
        .productsId(c.getProducts())
        .build();
    }

    public CategoryTable toEntity ( Category c){
        if (c==null) return null;
        return CategoryTable.builder()
            .id(c.getId())
            .name(c.getName().getValue())
            .products(c.getProductsId())
            .build();
    }

    
}
