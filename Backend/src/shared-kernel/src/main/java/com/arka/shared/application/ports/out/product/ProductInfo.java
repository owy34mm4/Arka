package com.arka.shared.application.ports.out.product;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class ProductInfo {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String currency;
    private Integer stock;
    private List<Long> categoriesIds;
    
    public static ProductInfo create(Long id, String name, String description, Integer price, String currency, Integer stock, List<Long> categoriesIds){
        return ProductInfo.builder()
        .id(id)
        .name(name)
        .description(description)
        .price(price)
        .currency(currency)
        .stock(stock)
        .categoriesIds(categoriesIds)
        .build();
    }
}
