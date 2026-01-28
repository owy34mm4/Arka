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
    
}
