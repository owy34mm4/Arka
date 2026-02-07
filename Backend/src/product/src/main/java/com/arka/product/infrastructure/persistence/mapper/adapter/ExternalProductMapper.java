package com.arka.product.infrastructure.persistence.mapper.adapter;


import org.springframework.stereotype.Component;

import com.arka.product.domain.model.Product;
import com.arka.product.domain.valueObjects.ProductCategoriesIds;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.infrastructure.externalMapper.gateway.IExternalMapper;

@Component
public class ExternalProductMapper implements IExternalMapper<ProductInfo,Product>{

    @Override
    public Product toDomain(ProductInfo info) {
        return Product.builder()
        .id(info.getId())
        .name(new ProductName(info.getName()))
        .description(new ProductDescription(info.getDescription()))
        .price(new ProductPrice( info.getPrice() , info.getCurrency() ) )
        .stock(new ProductStock(info.getStock()) )
        .categories(null)
        .categoriesIds(new ProductCategoriesIds(info.getCategoriesIds()))
        .shopingCartsIds(null)
        .ordersIds(null)
        .build();
    }

    @Override
    public ProductInfo toInfo(Product domain) {
        return ProductInfo.builder()
        .id(domain.getId())
        .name(domain.getName().getValue())
        .description(domain.getDescription().getValue())
        .price(domain.getPrice().getValue())
        .currency(domain.getPrice().getCurrency())
        .stock(domain.getStock().getValue())  
        .categoriesIds(domain.getCategoriesIds().getValues())

        .build();
    }
    
}
