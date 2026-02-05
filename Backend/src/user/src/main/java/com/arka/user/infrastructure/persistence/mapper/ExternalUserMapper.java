package com.arka.user.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.infrastructure.infoMapper.gateway.IInfoMapper;
import com.arka.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExternalUserMapper implements IInfoMapper<UserInfo,User> {

    @Override
    public User toDomain(UserInfo info) {
        return User.builder()
        .id(info.getId())
        .firstName(info.getFirstName())
        .lastName(info.getLastName())
        .firstSurname(info.getFirstSurname())
        .lastSurname(info.getLastSurname())
        .username(info.getUsername())
        .email(info.getEmail())
        .password(info.getPassword())
        .isActive(info.isActive())
        .lastLogin(info.getLastLogin())
        .createdAt(info.getCreatedAt())
        .role(null)
        .shoppingCartsIds(info.getShoppingCartsIds())
        .shopingCarts(info.getShopingCarts())
        .ordersIds(info.getOrdersIds())
        .build(); 
    }

    @Override
    public UserInfo toInfo(User domain) {
        return UserInfo.builder()
        .id(domain.getId())
        .firstName(domain.getFirstName())
        .lastName(domain.getLastName())
        .firstSurname(domain.getFirstSurname())
        .lastSurname(domain.getLastSurname())
        .email(domain.getEmail())
        .username(domain.getUsername())
        .password(domain.getPassword())
        .isActive(domain.isActive())
        .lastLogin(domain.getLastLogin())
        .createdAt(domain.getCreatedAt())
        .role(null)
        .shoppingCartsIds(domain.getShoppingCartsIds())
        .shopingCarts(domain.getShopingCarts())
        .ordersIds(domain.getOrdersIds())
        .build();
        
    }
    
}
