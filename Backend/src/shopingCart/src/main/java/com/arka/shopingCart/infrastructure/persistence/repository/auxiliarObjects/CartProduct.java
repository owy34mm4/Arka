package com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartProduct {
    private Long productId;  
    private String name;  
    private BigDecimal price;  
    private String currency;
}
