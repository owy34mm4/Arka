package com.arka.shopingCart.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCart;

public interface IJPAShopingCart extends JpaRepository<ShopingCart,Long>{
    
}
