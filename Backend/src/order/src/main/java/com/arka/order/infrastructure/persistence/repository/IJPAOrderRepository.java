package com.arka.order.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.order.infrastructure.persistence.entity.Order;


public interface IJPAOrderRepository extends JpaRepository<Order,Long>{
    
}
