package com.arka.product.infrastructure.persistence.repository.internal.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.product.infrastructure.persistence.entity.ProductTable;


public interface IJPAProductRepository extends JpaRepository<ProductTable,Long>{}
