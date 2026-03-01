package com.arka.product.infrastructure.persistence.repository.external.adapter;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductHistoryMapper;
import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductHistoryMapper;

import com.arka.product.infrastructure.persistence.repository.internal.gateway.IJPAProductHistoryRespository;
import com.arka.shared.application.ports.out.product.IProductHistoryDataPort;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductHistoryExternalRepositoryAdapter implements IProductHistoryDataPort{

    private final IJPAProductHistoryRespository productHistoryRepository;

    private final ExternalProductHistoryMapper externalProductHistoryMapper;

    private final PersistanceProductHistoryMapper persistanceProductHistoryMapper;


    @Override
    public boolean existsById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public List<ProductHisotryInfo> findAllById(List<Long> Ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public ProductHisotryInfo findById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ProductHisotryInfo save(ProductHisotryInfo pHI) {
        var productDomain = externalProductHistoryMapper.toDomain(pHI);
        var productTable = persistanceProductHistoryMapper.toEntity(productDomain);

        productTable = productHistoryRepository.save(productTable);
        
        productDomain = persistanceProductHistoryMapper.toDomain(productTable);
        return externalProductHistoryMapper.toInfo(productDomain);
        
    }
    
}
