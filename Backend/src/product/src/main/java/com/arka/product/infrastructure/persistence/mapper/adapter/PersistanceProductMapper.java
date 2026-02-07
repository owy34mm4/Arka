package com.arka.product.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.product.domain.model.Product;
import com.arka.product.domain.valueObjects.ProductCategoriesIds;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.product.infrastructure.persistence.entity.ProductTable;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;




@Component
public class PersistanceProductMapper implements IPersistanceMapper < Product , ProductTable > {

    public ProductTable toEntity(Product p){
        if (p==null) return null;
        return ProductTable.builder()
                .id(p.getId())
                .name(p.getName().getValue())
                .description(p.getDescription().getValue())
                .price(p.getPrice().getValue())
                .currency(p.getPrice().getCurrency())
                .stock(p.getStock().getValue())
                .categoriesId(p.getCategoriesIds().getValues())
            .build();
    }

    public  Product toDomain (ProductTable p){

        if (p==null) return null;

        return Product.builder()
        .id(p.getId())
        .name(new ProductName(p.getName()))
        .description(new ProductDescription(p.getDescription()))
        .price(new ProductPrice(p.getPrice(), p.getCurrency()) )
        .stock(new ProductStock(p.getStock()))
        .categoriesIds(new ProductCategoriesIds(p.getCategoriesId()))
        .shopingCartsIds(null)
        .ordersIds(null)
        .build();

    }
}
