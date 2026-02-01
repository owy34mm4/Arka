package com.arka.shared.application.ports.out.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class ProductInfo {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    
    public static ProductInfo create(Long id, String name, String description, Integer price, Integer stock){
        return ProductInfo.builder()
        .id(id)
        .name(name)
        .description(description)
        .price(price)
        .stock(stock)
        .build();
    }
}
