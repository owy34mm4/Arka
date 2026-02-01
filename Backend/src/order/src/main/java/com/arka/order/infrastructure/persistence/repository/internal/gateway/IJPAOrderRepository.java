package com.arka.order.infrastructure.persistence.repository.internal.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.order.infrastructure.persistence.entity.OrderTable;


public interface IJPAOrderRepository extends JpaRepository<OrderTable,Long>{
    
}
