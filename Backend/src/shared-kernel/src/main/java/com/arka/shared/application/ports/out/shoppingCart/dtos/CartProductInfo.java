package com.arka.shared.application.ports.out.shoppingCart.dtos;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartProductInfo {
    private Long productId;  
    private String name;  
    private Integer price;  
    private String currency;
}
