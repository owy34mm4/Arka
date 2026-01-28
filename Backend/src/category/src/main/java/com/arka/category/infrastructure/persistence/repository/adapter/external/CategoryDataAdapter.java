package com.arka.category.infrastructure.persistence.repository.adapter.external;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arka.category.infrastructure.persistence.entity.Category;
import com.arka.category.infrastructure.persistence.repository.IJPACategoryRepository;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.category.ICategoryDataPort;

import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;

@Component
@Builder
public class CategoryDataAdapter implements ICategoryDataPort {

    @Autowired
    private final IJPACategoryRepository repository;

    @Override
    public boolean exists(Long categoryId) {
        if (categoryId == null) {return false;}
        return repository.existsById(categoryId);
    }

    @Override
    public List<CategoryInfo> findAllById(List<Long> productIds) {
        if (productIds.isEmpty()) return null;
        List<CategoryInfo> categories = productIds.stream().filter(p -> p!=null).map( p -> repository.findById(p) ) 
        .map(Optional::get)
        .map(e -> CategoryInfo.create(e.getId(), e.getName()))
        .toList();
        return categories;
    }

    @Override
    public CategoryInfo findById(Long productIds) {
        if (productIds == null) {return CategoryInfo.builder().build();}
        Category entity = repository.findById(productIds).orElseThrow(() -> new EntityNotFoundException());
        return CategoryInfo.create(productIds, entity.getName());
    }



    
}
