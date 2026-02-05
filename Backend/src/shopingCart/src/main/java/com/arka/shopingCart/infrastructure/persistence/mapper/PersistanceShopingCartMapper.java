package com.arka.shopingCart.infrastructure.persistence.mapper;


import org.springframework.stereotype.Component;

import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;
import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartTable;

@Component
public class PersistanceShopingCartMapper implements IPersistanceMapper<ShopingCart, ShopingCartTable> {
    
    public ShopingCart toDomain (ShopingCartTable sc){
        if (sc==null) return null;
        return ShopingCart.builder()
        .id(sc.getId())
        .createdAt(sc.getCreatedAt())
        .ownerId(sc.getOwnerId())
        .productsIds(sc.getProductsIds())
        .build();
    }

    public ShopingCartTable toEntity (ShopingCart sc){
        if (sc==null) return null;
        return ShopingCartTable.builder()
            .id(sc.getId())
            .createdAt(sc.getCreatedAt())
            .ownerId(sc.getOwnerId())
            .productsIds(sc.getProductsIds())
            .build();
    }
}
