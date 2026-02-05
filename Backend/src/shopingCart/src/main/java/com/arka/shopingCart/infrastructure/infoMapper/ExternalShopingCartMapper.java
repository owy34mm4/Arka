package com.arka.shopingCart.infrastructure.infoMapper;

import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.shared.infrastructure.externalMapper.gateway.IExternalMapper;
import com.arka.shopingCart.domain.model.ShopingCart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ExternalShopingCartMapper implements IExternalMapper<ShopingCartInfo,ShopingCart> {
    
    @Override
    public ShopingCart toDomain(ShopingCartInfo info) {
        return ShopingCart.builder()
            .id(info.getId())
            .createdAt(info.getCreatedAt())
            .ownerId(info.getOwnerId())
            .owner(info.getOwner())
            .productsIds(info.getProductsIds())
            .products(info.getProducts())
            .isOrdered(info.isOrdered())
        .build();
    }

    @Override
    public ShopingCartInfo toInfo(ShopingCart domain) {
        return ShopingCartInfo.builder()
            .id(domain.getId())
            .createdAt(domain.getCreatedAt())
            .ownerId(domain.getOwnerId())
            .owner(domain.getOwner())
            .productsIds(domain.getProductsIds())
            .products(domain.getProducts())
            .isOrdered(domain.isOrdered())
        .build();
    }
    
}
