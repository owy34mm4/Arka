package com.arka.category.infrastructure.persistence.repository.external.adapter;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Repository;

import com.arka.category.infrastructure.persistence.entity.CategoryTable;
import com.arka.category.infrastructure.persistence.repository.external.gateway.ICategoryExternalRepository;
import com.arka.category.infrastructure.persistence.repository.internal.gateway.IJPACategoryRepository;
import com.arka.shared.application.ports.out.category.CategoryInfo;


import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryDataAdapter implements ICategoryExternalRepository {

    
    private final IJPACategoryRepository repository;


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
        CategoryTable entity = repository.findById(productIds).orElseThrow(() -> new EntityNotFoundException());
        return CategoryInfo.create(productIds, entity.getName());
    }

    @Override
    public boolean existsById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }



    
}
