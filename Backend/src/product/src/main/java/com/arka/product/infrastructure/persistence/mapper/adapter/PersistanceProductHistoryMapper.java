package com.arka.product.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.product.domain.model.ProductHistory;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.product.infrastructure.persistence.entity.ProductHistoryTable;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;


@Component
public class PersistanceProductHistoryMapper implements IPersistanceMapper< ProductHistory , ProductHistoryTable> {

    @Override
    public ProductHistory toDomain(ProductHistoryTable entity) {
        if (entity ==null) return null;
        return ProductHistory.builder()
            .id(entity.getId())
            .name(new ProductName(entity.getName()))
            .description(new ProductDescription(entity.getDescription()))
            .price(new ProductPrice(entity.getPrice() , entity.getCurrency()))
            .stock(new ProductStock(entity.getStock()))
            .createdAt(entity.getCreatedAt())
            .createdById(entity.getCreatedById())
            .modifiedAt(entity.getModifiedAt())
            .modifiedById(entity.getModifiedById())
            .productId(entity.getProductId())
        .build();
    }

    @Override
    public ProductHistoryTable toEntity(ProductHistory model) {
        if (model ==null) return null;
        return ProductHistoryTable.builder()
            .id(model.getId())
            .name(model.getName().getValue())
            .description(model.getDescription().getValue())
            .price(model.getPrice().getValue())
            .currency(model.getPrice().getCurrency())
            .stock(model.getStock().getValue())
            .createdAt(model.getCreatedAt())
            .createdById(model.getCreatedById())
            .modifiedAt(model.getModifiedAt())
            .modifiedById(model.getModifiedById())
            .productId(model.getProductId())
        .build();
    }
    
}
