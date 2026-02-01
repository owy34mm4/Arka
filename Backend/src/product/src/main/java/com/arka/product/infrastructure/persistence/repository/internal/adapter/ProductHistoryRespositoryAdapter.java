package com.arka.product.infrastructure.persistence.repository.internal.adapter;


import org.springframework.stereotype.Repository;

import com.arka.product.application.port.out.IProductHistoryPort;
import com.arka.product.domain.model.ProductHistory;


import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductHistoryMapper;
import com.arka.product.infrastructure.persistence.repository.internal.gateway.IJPAProductHistoryRespository;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class ProductHistoryRespositoryAdapter implements IProductHistoryPort{

    
    private final IJPAProductHistoryRespository jpaProductHistory;
   
    private final PersistanceProductHistoryMapper productHistoryMapper;

    
    @Override
    public ProductHistory save(ProductHistory productToRecord) {
        if (productToRecord ==null) return null;
        var entity = productHistoryMapper.toEntity(productToRecord);
        entity = jpaProductHistory.save(entity);
        return productHistoryMapper.toDomain(entity);
       
    }

    @Override
    public ProductHistory findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
