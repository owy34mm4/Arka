package com.arka.category.application.port.out;


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

    com.arka.category.domain.model.Category save(com.arka.category.domain.model.Category category);
}
