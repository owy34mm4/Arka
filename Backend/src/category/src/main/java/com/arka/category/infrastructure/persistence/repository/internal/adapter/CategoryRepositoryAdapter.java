package com.arka.category.infrastructure.persistence.repository.internal.adapter;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.domain.model.Category;
import com.arka.category.infrastructure.persistence.mapper.PersistanceCategoryMapper;
import com.arka.category.infrastructure.persistence.repository.internal.gateway.IJPACategoryRepository;
import com.arka.shared.domain.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements ICategoryRepository {
    
    private final IJPACategoryRepository categoryRepository;
    private final PersistanceCategoryMapper persistanceCategoryMapper;

  

    @Override
    public Category save (Category category){
        return persistanceCategoryMapper.toDomain(
            categoryRepository.save(
                persistanceCategoryMapper.toEntity(category)
                )
        );

    }


    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }


    @Override
    public Category findById(Long id) {
        return persistanceCategoryMapper.toDomain(categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("Category")));
    }


    @Override
    public List<Category> findAllById(List<Long> ids) {
        return categoryRepository.findAllById(ids).stream().map(
            c -> persistanceCategoryMapper.toDomain(c)
        ).toList();
    }



}
