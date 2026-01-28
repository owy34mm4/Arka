package com.arka.product.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.product.infrastructure.persistence.entity.ProductHistoryTable;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;

import lombok.Builder;


@Component
@Builder
public class PersistanceProductHistoryMapper implements IPersistanceMapper< com.arka.product.domain.model.ProductHistory , ProductHistoryTable> {

    @Override
    public com.arka.product.domain.model.ProductHistory toDomain(ProductHistoryTable entity) {
        if (entity ==null) return null;
        return com.arka.product.domain.model.ProductHistory.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .price(entity.getPrice())
            .stock(entity.getStock())
            .createdAt(entity.getCreatedAt())
            .productId(entity.getProductId())
        .build();
    }

    @Override
    public ProductHistoryTable toEntity(com.arka.product.domain.model.ProductHistory model) {
        if (model ==null) return null;
        return ProductHistoryTable.builder()
            .id(model.getId())
            .name(model.getName())
            .description(model.getDescription())
            .price(model.getPrice())
            .stock(model.getStock())
            .createdAt(null)
            .productId(model.getProductId())
        .build();
    }
    
}
