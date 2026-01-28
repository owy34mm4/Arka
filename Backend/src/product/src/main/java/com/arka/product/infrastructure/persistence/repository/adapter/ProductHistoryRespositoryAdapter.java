package com.arka.product.infrastructure.persistence.repository.adapter;


import org.springframework.stereotype.Component;

import com.arka.product.application.port.out.IProductHistoryPort;
import com.arka.product.domain.model.ProductHistory;
import com.arka.product.infrastructure.persistence.entity.ProductTable;
import com.arka.product.infrastructure.persistence.entity.ProductHistoryTable;
import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductHistoryMapper;
import com.arka.product.infrastructure.persistence.repository.gateway.IJPAProductHistoryRespository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductHistoryRespositoryAdapter implements IProductHistoryPort{

    
    IJPAProductHistoryRespository jpaProductHistory;
   
    PersistanceProductHistoryMapper productHistoryMapper;

    
    @Override
    public ProductHistory save(ProductTable productToRecord) {
        if (productToRecord ==null) return null;
        ProductHistory pHistory = com.arka.product.domain.model.ProductHistory.createFromProductEntity(productToRecord);
        ProductHistoryTable savedProductRecordEntity = jpaProductHistory.save(productHistoryMapper.toEntity(pHistory));
        ProductHistory savedProductRecordModel = productHistoryMapper.toDomain(savedProductRecordEntity);
        return savedProductRecordModel;
    }

    @Override
    public ProductHistory findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
