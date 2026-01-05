package com.Arka.infrastructure.adapter.persistence.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Arka.infrastructure.adapter.persistence.entity.ShopingCart;

public interface IShopingCart extends JpaRepository<ShopingCart,Long>{
    
}
