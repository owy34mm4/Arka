package com.Arka.infrastructure.adapter.persistence.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<com.Arka.infrastructure.adapter.persistence.entity.Product,Long>{
    
}
