package com.arka.shared.application.ports.out.product;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductHisotryInfo {
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    private Date createdAt;

    private Long createdById;

    private Date modifiedAt;

    private Long modifiedById;
    
    private Long productId;
}
