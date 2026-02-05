package com.arka.product.infrastructure.persistence.mapper.adapter;


import org.springframework.stereotype.Component;

import com.arka.product.domain.model.Product;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.infrastructure.externalMapper.gateway.IExternalMapper;

@Component
public class ExternalProductMapper implements IExternalMapper<ProductInfo,Product>{

    @Override
    public Product toDomain(ProductInfo info) {
        return Product.builder()
        .id(info.getId())
        .name(info.getName())
        .description(info.getDescription())
        .price(info.getPrice())
        .stock(info.getStock())  
        .categories(null)
        .categoriesIds(null)
        .shopingCartsIds(null)
        .ordersIds(null)
        .build();
    }

    @Override
    public ProductInfo toInfo(Product domain) {
        return ProductInfo.builder()
        .id(domain.getId())
        .name(domain.getName())
        .description(domain.getDescription())
        .price(domain.getPrice())
        .stock(domain.getStock())  
        .build();
    }
    
}
