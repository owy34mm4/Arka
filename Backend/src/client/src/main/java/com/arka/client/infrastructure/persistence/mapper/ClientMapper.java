package com.arka.client.infrastructure.persistence.mapper;


import com.arka.client.infrastructure.persistence.entity.Client;

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
                    //.shopingCartsIds(c.getShoppingCartsIds())
                    //.ordersIds(c.getOrdersIds())
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
                    //.shoppingCartsIds(c.getShopingCartsIds())
                    //.ordersIds(c.getOrdersIds())
                
                .build();

    }
}
