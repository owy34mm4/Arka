package com.arka.shopingCart.infrastructure.persistence.repository.internal.gateway;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartDetailTable;

public interface IJPAShopingCartDetail extends JpaRepository<ShopingCartDetailTable,Long>{
    List<ShopingCartDetailTable> findByShopingCartId_Id(Long cartId);  
    List<ShopingCartDetailTable> findByProductId(Long productId);
}
