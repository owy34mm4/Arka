package com.arka.shopingCart.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.shoppingCart.dtos.PendingCartDTOInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects.PendingCartDTO;

@Component
public class ExternalPendingCartDTOMapper implements IExternalMapper<PendingCartDTOInfo, PendingCartDTO> {

    @Override
    public PendingCartDTO toDomain(PendingCartDTOInfo info) {
        return PendingCartDTO.builder()
            .cartId(info.getCartId())
            .createdAt(info.getCreatedAt())
            .ownerId(info.getOwnerId())
            .firstName(info.getFirstName())
            .firstSurname(info.getFirstSurname())
            .email(info.getEmail())
            .productsIds(info.getProductsIds())
        .build();
    }

    @Override
    public PendingCartDTOInfo toInfo(PendingCartDTO domain) {
        return PendingCartDTOInfo.builder()
            .cartId(domain.getCartId())
            .createdAt(domain.getCreatedAt())
            .ownerId(domain.getOwnerId())
            .firstName(domain.getFirstName())
            .firstSurname(domain.getFirstSurname())
            .email(domain.getEmail())
            .productsIds(domain.getProductsIds())
        .build();
    }
    
}
