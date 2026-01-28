package com.arka.shopingCart.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartDetail;

public interface IJPAShopingCartDetail extends JpaRepository<ShopingCartDetail,Long>{
    List<ShopingCartDetail> findByShopingCartId(Long cartId);  
    List<ShopingCartDetail> findByProductId(Long productId);
}
