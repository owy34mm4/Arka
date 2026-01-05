package com.arka.category.infrastructure.persistence.repository;

import org.springframework.stereotype.Component;

import com.arka.category.infrastructure.persistence.entity.Category;
import com.arka.category.infrastructure.persistence.mapper.CategoryMapper;

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
public class CategoryRepositoryAdapter implements com.arka.category.application.port.out.ICategoryRepository {
    
    private final IJPACategoryRepository jpaRepository;
    private final CategoryMapper mapper;

    public CategoryRepositoryAdapter(IJPACategoryRepository jpaRepository,CategoryMapper mapper){
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public com.arka.category.domain.model.Category save (com.arka.category.domain.model.Category category){
        // Convertir >> Dominio -> JPA
        Category entity = mapper.toEntity(category);

        // Guardar con SpringData
        Category savedEntity = jpaRepository.save(entity);

        //Convertir >> JPA -> Dominio >>> Retornar
        return mapper.toModel(savedEntity);

    }

    public boolean existsByName (com.arka.category.domain.model.Category category){
        return false;
    }



}
