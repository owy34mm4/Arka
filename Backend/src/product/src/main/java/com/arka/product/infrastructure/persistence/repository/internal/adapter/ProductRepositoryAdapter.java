package com.arka.product.infrastructure.persistence.repository.internal.adapter;


import java.util.List;

import org.springframework.stereotype.Repository;


import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.domain.model.Product;

import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductMapper;
import com.arka.product.infrastructure.persistence.repository.auxiliarObjects.LowStockProductDTO;
import com.arka.product.infrastructure.persistence.repository.auxiliarObjects.LowStockProductDTOMapper;
import com.arka.product.infrastructure.persistence.repository.internal.gateway.IJPAProductRepository;
import com.arka.shared.domain.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryAdapter implements IProductRepositoryPort{
    
    private final IJPAProductRepository productRepository;

    private final PersistanceProductMapper peristanceProductMapper;

    private final LowStockProductDTOMapper lowStockDTOPMapper;
   
    @Override
    public Product save(Product product) {
        return peristanceProductMapper.toDomain(
            productRepository.save(
                peristanceProductMapper.toEntity(product)
            )
        );
    }

    @Override
    public Product findById(Long id) {
        return peristanceProductMapper.toDomain(
            productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product"))
        );
    }

    @Override
    public List<Product> findAllById(List<Long> ids) {
       return productRepository.findAllById(ids).stream().map(
        p -> peristanceProductMapper.toDomain(p)
       ).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<LowStockProductDTO> findProductsBelowStock(Integer threshold) {
        return lowStockDTOPMapper.fromRawRows(
            productRepository.findProductsBelowStock(threshold).orElseThrow(()-> new NotFoundException("ProductsLowStock"))
        );
    }
}
