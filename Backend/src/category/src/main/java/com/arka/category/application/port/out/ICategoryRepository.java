package com.arka.category.application.port.out;

import java.util.List;

import com.arka.category.domain.model.Category;

/**  
 * PUERTO DE SALIDA (Driven Port)  
 *   
 * Define QUÉ necesita la aplicación del mundo exterior (persistencia).  
 * Esta interfaz será IMPLEMENTADA por Infrastructure.  
 *   
 * ⚠️ Trabaja con entidades de DOMINIO, no con entidades JPA.  
 */
public interface ICategoryRepository {

    boolean existsByName (String name);

    Category findById(Long id);

    List<Category> findAllById (List<Long> ids);

    Category save(Category category);
}
