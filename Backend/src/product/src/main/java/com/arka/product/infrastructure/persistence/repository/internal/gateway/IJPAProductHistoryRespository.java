package com.arka.product.infrastructure.persistence.repository.internal.gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJPAProductHistoryRespository extends JpaRepository<com.arka.product.infrastructure.persistence.entity.ProductHistoryTable,Long> { }
