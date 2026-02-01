package com.arka.category.infrastructure.persistence.repository.internal.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.category.infrastructure.persistence.entity.CategoryTable;

@Repository
public interface IJPACategoryRepository extends JpaRepository<CategoryTable,Long>{

    //@Query("SELECT COUNT(p) > 0 FROM Category c WHERE LOWER(c.name) = LOWER(:name)")  
    boolean existsByNameIgnoreCase(String name);

}
