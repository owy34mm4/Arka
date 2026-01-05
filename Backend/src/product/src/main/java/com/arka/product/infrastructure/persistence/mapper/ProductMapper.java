package com.arka.product.infrastructure.persistence.mapper;

import com.arka.category.infrastructure.persistence.mapper.CategoryMapper;
import com.arka.product.infrastructure.persistence.entity.Product;
import com.arka.shopingCart.infrastructure.persistence.mapper.ShopingCartMapper;

public class ProductMapper {
    
    public static Product toEntity(com.arka.product.domain.model.Product p){
        if (p==null) return null;
        return com.arka.product.infrastructure.persistence.entity.Product.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stock(p.getStock())
                .categories(p.getCategories().stream().map(
                    c -> CategoryMapper.toEntity(c))
                .toList()
                )
                .shoppingCarts(p.getShopingCarts().stream().map(
                        sc-> ShopingCartMapper.toEntity(sc)
                    )
                .toList())
                .orders(null)
            .build();
    }


    public static com.arka.product.domain.model.Product toDomain (Product p){

        if (p==null) return null;

        return com.arka.product.domain.model.Product.builder()
        .id(p.getId())
        .name(p.getName())
        .description(p.getDescription())
        .price(p.getPrice())
        .stock(p.getStock())
        .categories(p.getCategories().stream().map(
                c-> CategoryMapper.toDomain(c)
            )
        .toList())
        .shopingCarts(p.getShoppingCarts().stream().map(
            sc-> ShopingCartMapper.toDomain(sc)
        )
        .toList())
        .build();

    }
}
