package com.arka.product.domain.model;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductHistory {
    
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    
    private Date createdAt;

    //private Long modifiedBy; //TO-DO == Link w ClientId
    
    private Long productId;

    public static ProductHistory createFromProductEntity(com.arka.product.infrastructure.persistence.entity.ProductTable entity){

        return ProductHistory.builder()
            .id(null)
            .name(entity.getName())
            .description(entity.getDescription())
            .price(entity.getPrice())
            .stock(entity.getStock())
            .createdAt(null)
            .productId(entity.getId())
        .build();

    }
}
