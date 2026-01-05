package com.arka.order.infrastructure.persistence.mapper;

import com.arka.client.infrastructure.persistence.mapper.ClientMapper;
import com.arka.order.infrastructure.persistence.entity.Order;

public class OrderMapper {
    
    public static com.arka.order.domain.model.Order toDomain(Order o){
        if (o==null) return null;
        return  com.arka.order.domain.model.Order.builder()
                .id(o.getId())
                .owner(ClientMapper.toDomain(o.getOwner()))
                .products(o.getProducts().stream().map(
                    p-> ProductMapper.toDomain(p)
                ).toList())
                .build();

    }

    public static Order toEntity (com.arka.order.domain.model.Order o){
        if (o==null) return null;
        return Order.builder()
        .id(o.getId())
        .owner(ClientMapper.toEntity(o.getOwner()))
        .products(o.getProducts().stream().map(
            p-> ProductMapper.toEntity(p)
        ).toList())
        .build();

    }
}
