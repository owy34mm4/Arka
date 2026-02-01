package com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart;

import java.util.List;

import com.arka.shared.application.ports.out.product.ProductInfo;
//import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shopingCart.domain.model.ShopingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResponseSaveCart {
    private Long id;
    //private UserInfo owner;
    private String ownerUsername;
    private Long ownerId;
    private List<ProductInfo> products;
    private String message;

    public static ResponseSaveCart createFromModel(ShopingCart model, String msg){
        return ResponseSaveCart.builder()
        .id(model.getId())
        .ownerUsername(model.getOwner().getUsername())
        .ownerId(model.getOwner().getId())
        .products(model.getProducts())
        .message(msg)

        .build();

    }
}
