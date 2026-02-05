package com.arka.product.infrastructure.persistence.repository.external.adapter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.persistence.entity.ProductTable;
import com.arka.product.infrastructure.persistence.mapper.adapter.PersistanceProductMapper;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductExternalRepository;
import com.arka.product.infrastructure.persistence.repository.internal.gateway.IJPAProductRepository;

import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.domain.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductDataAdapter implements IProductExternalRepository {

    private final IJPAProductRepository productRepository;

    private final PersistanceProductMapper persistanceProductMapper;



    @Override
    public ProductInfo findById(Long productIds) {
        ProductTable productEntity = productRepository.findById(productIds).orElseThrow( ()->new NotFoundException("Producto") );

        Product productModel = persistanceProductMapper.toDomain(productEntity);

        return ProductInfo.create(
            productModel.getId(),
            productModel.getName(),
            productModel.getDescription(),
            productModel.getPrice(),
            productModel.getStock()
        );
    }

    @Override
    public List<ProductInfo> findAllById(List<Long> productsIds) {
       return productsIds.stream()
            .filter(pId -> pId!=null)
            .map(pId -> productRepository.findById(pId).orElseThrow(()-> new NotFoundException("Product")))
            .map(p -> ProductInfo.create(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock()))
            .toList()
            ;
    }

    @Override
    public boolean existsById(Long Id) {
        return productRepository.existsById(Id);
    }
    
}
