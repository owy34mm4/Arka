package com.arka.shopingCart.infrastructure.persistence.repository.internal.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartTable;

public interface IJPAShopingCart extends JpaRepository<ShopingCartTable,Long>{
    
}
