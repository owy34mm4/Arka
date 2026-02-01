package com.arka.category.infrastructure.persistence.repository.internal.adapter;


import org.springframework.stereotype.Repository;

import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.domain.model.Category;
import com.arka.category.infrastructure.persistence.entity.CategoryTable;
import com.arka.category.infrastructure.persistence.mapper.PersistanceCategoryMapper;
import com.arka.category.infrastructure.persistence.repository.internal.gateway.IJPACategoryRepository;


import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements ICategoryRepository {
    
    private final IJPACategoryRepository categoryRepository;
    private final PersistanceCategoryMapper persistanceCategoryMapper;

  

    @Override
    public Category save (Category category){
        // Convertir >> Dominio -> JPA
        CategoryTable entity = persistanceCategoryMapper.toEntity(category);

        // Guardar con SpringData
        entity = categoryRepository.save(entity);

        //Convertir >> JPA -> Dominio >>> Retornar
        return persistanceCategoryMapper.toDomain(entity);

    }


    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }



}
