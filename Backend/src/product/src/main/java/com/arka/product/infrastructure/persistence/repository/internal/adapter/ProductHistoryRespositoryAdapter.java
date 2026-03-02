package com.arka.product.infrastructure.persistence.repository.internal.adapter;


import org.springframework.stereotype.Repository;

import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.domain.model.ProductHistory;


import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductHistoryMapper;
import com.arka.product.infrastructure.persistence.repository.internal.gateway.IJPAProductHistoryRespository;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class ProductHistoryRespositoryAdapter implements IProductHistoryRepositoryPort{

    
    private final IJPAProductHistoryRespository productHistoryRepository;
   
    private final PersistanceProductHistoryMapper persistanceProductHistoryeMapper;

    
    @Override
    public ProductHistory save(ProductHistory productToRecord) {
        return persistanceProductHistoryeMapper.toDomain(
            productHistoryRepository.save(
                persistanceProductHistoryeMapper.toEntity(productToRecord)
            )
        );
       
    }

    @Override
    public ProductHistory findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
