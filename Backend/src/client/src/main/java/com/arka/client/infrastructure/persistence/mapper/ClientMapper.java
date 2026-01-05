package com.arka.client.infrastructure.persistence.mapper;


import com.arka.client.infrastructure.persistence.entity.Client;
import com.arka.order.infrastructure.persistence.mapper.OrderMapper;
import com.arka.shopingCart.infrastructure.persistence.mapper.ShopingCartMapper;

public class ClientMapper {


    public static Client toEntity (com.arka.client.domain.model.Client c){
        return Client.builder().id(c.getId())
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .firstSurname(c.getFirstSurname())
                    .lastSurname(c.getLastSurname())
                    .username(c.getUsername())
                    .password(c.getPassword())
                    .isActive(c.isActive())
                    .lastLogin(c.getLastLogin())
                    .createdAt(c.getCreatedAt())
                    .shoppingCarts(c.getShoppingCarts().stream().map(
                        sc-> ShopingCartMapper.toEntity(sc)
                    )
                    .toList())
                    .orders(c.getOrders().stream().map(
                        o-> OrderMapper.toEntity(o)
                    ).toList())
                .build();

    }

    public static com.arka.client.domain.model.Client toDomain(Client c){

        return com.arka.client.domain.model.Client.builder().id(c.getId())
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .firstSurname(c.getFirstSurname())
                    .lastSurname(c.getLastSurname())
                    .username(c.getUsername())
                    .password(c.getPassword())
                    .isActive(c.isActive())
                    .lastLogin(c.getLastLogin())
                    .createdAt(c.getCreatedAt())
                    .shoppingCarts(c.getShoppingCarts().stream().map(
                        sc-> ShopingCartMapper.toDomain(sc)
                    ).toList())
                    .orders(c.getOrders().stream().map(
                        o-> OrderMapper.toDomain(o)
                    ).toList())
                
                .build();

    }
}
