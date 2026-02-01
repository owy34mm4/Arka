package com.arka.product.infrastructure.persistence.repository.internal.adapter;


import org.springframework.stereotype.Repository;


import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.domain.model.Product;

import com.arka.product.infrastructure.persistence.entity.ProductTable;
import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductMapper;
import com.arka.product.infrastructure.persistence.repository.internal.gateway.IJPAProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryAdapter implements IProductRepositoryPort{
    
    private final IJPAProductRepository jpaProduct;

    private final PersistanceProductMapper persistanceMapper;
   
    @Override
    public Product save(Product product) {
        if (product==null ){return null;}

        ProductTable nProduct = persistanceMapper.toEntity(product);
        ProductTable savedEntity=jpaProduct.save(nProduct);
        Product savedModel = persistanceMapper.toDomain(savedEntity);
        return savedModel;
    }

    @Override
    public Product findById(Long id) {
        if (id==null) return null;
        ProductTable entity = jpaProduct.findById(id).get();
        Product model = persistanceMapper.toDomain(entity);
        return model;
    }
    
}
