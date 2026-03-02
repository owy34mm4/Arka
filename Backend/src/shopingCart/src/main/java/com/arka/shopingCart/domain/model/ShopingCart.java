package com.arka.shopingCart.domain.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
@Builder
public class ShopingCart {
    private Long id;
    private LocalDateTime createdAt;
    private Long ownerId;
    private UserInfo owner;
    private List<Long> productsIds;
    private List<ProductInfo> products;
    private boolean isOrdered;
    

    public boolean checkOwnership(Long ownerId){
        return true;
    }
}
