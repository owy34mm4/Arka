package com.arka.category.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.category.infrastructure.persistence.entity.Category;

@Repository
public interface IJPACategoryRepository extends JpaRepository<Category,Long>{

    //@Query("SELECT COUNT(p) > 0 FROM ProductJpaEntity p WHERE LOWER(p.name) = LOWER(:name)")  
    //boolean existsByNameIgnoreCase(String name);

}
