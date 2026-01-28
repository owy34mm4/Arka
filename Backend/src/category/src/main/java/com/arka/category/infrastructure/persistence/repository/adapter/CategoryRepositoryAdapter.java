package com.arka.category.infrastructure.persistence.repository.adapter;

import org.springframework.stereotype.Component;

import com.arka.category.infrastructure.persistence.entity.Category;
import com.arka.category.infrastructure.persistence.mapper.CategoryMapper;
import com.arka.category.infrastructure.persistence.repository.IJPACategoryRepository;

import lombok.Getter;
import lombok.Setter;

/**  
 * ADAPTADOR DE PERSISTENCIA - Implementa el puerto de salida.  
 *   
 * Este adaptador:  
 * - Implementa la interfaz de application (ICategoryRepository)  
 * - Usa Spring Data JPA internamente  
 * - Convierte entre entidades de dominio y JPA  
 *   
 * El dominio NO sabe que estamos usando JPA.  
 */
@Component
@Getter
@Setter
public class CategoryRepositoryAdapter implements com.arka.category.application.port.out.ICategoryRepository {
    
    private final IJPACategoryRepository jpaRepository;
    private final CategoryMapper mapper;

    public CategoryRepositoryAdapter(IJPACategoryRepository jpaRepository,CategoryMapper mapper){
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public com.arka.category.domain.model.Category save (com.arka.category.domain.model.Category category){
        if (category == null) return com.arka.category.domain.model.Category.builder().build();
        // Convertir >> Dominio -> JPA
        Category entity = mapper.toEntity(category);

        // Guardar con SpringData
        Category savedEntity = jpaRepository.save(entity);

        //Convertir >> JPA -> Dominio >>> Retornar
        return mapper.toDomain(savedEntity);

    }


    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }



}
