package com.arka.category.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.arka.category.domain.model.Category;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;

@Component
public class ExternalCategoryMapper implements IExternalMapper<CategoryInfo, Category>{

    @Override
    public Category toDomain(CategoryInfo info) {
       return Category.builder()
            .id(info.getId())
            .name(info.getName())
            .products(null)
            .productsId(null)
        .build();
    }

    @Override
    public CategoryInfo toInfo(Category domain) {
        return CategoryInfo.builder()
            .id(domain.getId())
            .name(domain.getName())
        .build();
    }
    
}
