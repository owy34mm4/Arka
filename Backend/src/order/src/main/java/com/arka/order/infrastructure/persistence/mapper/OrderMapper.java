package com.arka.order.infrastructure.persistence.mapper;


import com.arka.order.infrastructure.persistence.entity.Order;

public class OrderMapper {
    
    public static com.arka.order.domain.model.Order toDomain(Order o){
        if (o==null) return null;
        return  com.arka.order.domain.model.Order.builder()
                .id(o.getId())
                .ownerId(o.getOwnerId())
                //.productsIds(o.getProductsIds())
                .build();
    }

    public static Order toEntity (com.arka.order.domain.model.Order o){
        if (o==null) return null;
        return Order.builder()
        .id(o.getId())
        .ownerId(o.getOwnerId())
        //.productsIds(o.getProductsIds())
        .build();

    }
}
