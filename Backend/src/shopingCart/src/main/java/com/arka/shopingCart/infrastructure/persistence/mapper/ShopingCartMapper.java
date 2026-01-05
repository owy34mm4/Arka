package com.arka.shopingCart.infrastructure.persistence.mapper;

import com.arka.client.infrastructure.persistence.mapper.ClientMapper;
import com.arka.product.infrastructure.persistence.mapper.ProductMapper;
import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCart;

public class ShopingCartMapper {
    
    public static  com.arka.shopingCart.domain.model.ShopingCart toDomain (ShopingCart sc){
        if (sc==null) return null;
        return com.arka.shopingCart.domain.model.ShopingCart.builder()
        .id(sc.getId())
        .createdAt(sc.getCreatedAt())
        .owner(ClientMapper.toDomain(sc.getOwner()))
        .products(sc.getProducts().stream().map(
            p-> ProductMapper.toDomain(p)
        ).toList())
        .build();
    }

    public static ShopingCart toEntity (com.arka.shopingCart.domain.model.ShopingCart sc){
        if (sc==null) return null;
        return ShopingCart.builder()
            .id(sc.getId())
            .createdAt(sc.getCreatedAt())
            .owner(ClientMapper.toEntity(sc.getOwner()))
            .products(sc.getProducts().stream().map(
                p-> ProductMapper.toEntity(p)
            ).toList())
            .build();
    }
}
