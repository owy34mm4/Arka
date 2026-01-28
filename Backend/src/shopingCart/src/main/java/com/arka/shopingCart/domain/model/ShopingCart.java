package com.arka.shopingCart.domain.model;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
@Builder
public class ShopingCart {
    private Long id;
    private Date createdAt;
    private Long ownerId;
    private List<Long> productsIds;
    
}
