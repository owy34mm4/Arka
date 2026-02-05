package com.arka.product.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.product.domain.model.ProductHistory;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.infrastructure.externalMapper.gateway.IExternalMapper;

@Component
public class ExternalProductHistoryMapper implements IExternalMapper<ProductHisotryInfo, ProductHistory>{

    @Override
    public ProductHistory toDomain(ProductHisotryInfo info) {
        return ProductHistory.builder()
        .id(info.getId())
        .name(info.getName())
        .description(info.getDescription())
        .price(info.getPrice())
        .stock(info.getStock())
        .createdAt(info.getCreatedAt())
        .createdById(info.getCreatedById())
        .modifiedAt(info.getModifiedAt())
        .modifiedById(info.getModifiedById())
        .productId(info.getProductId())
        .build();
    }

    @Override
    public ProductHisotryInfo toInfo(ProductHistory domain) {
        return ProductHisotryInfo.builder()
        .id(domain.getId())
        .name(domain.getName())
        .description(domain.getDescription())
        .price(domain.getPrice())
        .stock(domain.getStock())
        .createdAt(domain.getCreatedAt())
        .createdById(domain.getCreatedById())
        .modifiedAt(domain.getModifiedAt())
        .modifiedById(domain.getModifiedById())
        .productId(domain.getProductId())
        .build();
    }
    
}
