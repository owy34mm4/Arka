package com.arka.shopingCart.infrastructure.persistence.mapper;


import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCart;

public class ShopingCartMapper {
    
    public static  com.arka.shopingCart.domain.model.ShopingCart toDomain (ShopingCart sc){
        if (sc==null) return null;
        return com.arka.shopingCart.domain.model.ShopingCart.builder()
        .id(sc.getId())
        .createdAt(sc.getCreatedAt())
        .ownerId(sc.getOwnerId())
        //.productsIds(sc.getProductsIds())
        .build();
    }

    public static ShopingCart toEntity (com.arka.shopingCart.domain.model.ShopingCart sc){
        if (sc==null) return null;
        return ShopingCart.builder()
            .id(sc.getId())
            .createdAt(sc.getCreatedAt())
            .ownerId(sc.getOwnerId())
            //.productsIds(sc.getProductsIds())
            .build();
    }
}
