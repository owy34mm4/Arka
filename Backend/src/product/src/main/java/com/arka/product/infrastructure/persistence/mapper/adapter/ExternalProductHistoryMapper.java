package com.arka.product.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.product.domain.model.ProductHistory;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;

@Component
public class ExternalProductHistoryMapper implements IExternalMapper<ProductHisotryInfo, ProductHistory>{

    @Override
    public ProductHistory toDomain(ProductHisotryInfo info) {
        return ProductHistory.builder()
        .id(info.getId())
        .name(new ProductName(info.getName()))
        .description(new ProductDescription(info.getDescription()))
        .price(new ProductPrice(info.getPrice() , info.getCurrency() ) )
        .stock(new ProductStock(info.getStock()))
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
        .name(domain.getName().getValue())
        .description(domain.getDescription().getValue())
        .price(domain.getPrice().getValue())
        .currency(domain.getPrice().getCurrency())
        .stock(domain.getStock().getValue())
        .createdAt(domain.getCreatedAt())
        .createdById(domain.getCreatedById())
        .modifiedAt(domain.getModifiedAt())
        .modifiedById(domain.getModifiedById())
        .productId(domain.getProductId())
        .build();
    }
    
}
