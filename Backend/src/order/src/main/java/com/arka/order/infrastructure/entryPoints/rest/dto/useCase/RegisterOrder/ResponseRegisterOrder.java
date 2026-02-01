package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder;



import java.util.List;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseRegisterOrder {
    Long id;
    UserInfo owner;
    OrderState state;
    List<ProductInfo> products;
    String message;

    public static ResponseRegisterOrder createFromModel(Order o, String msg){
        return ResponseRegisterOrder.builder()
        .id(o.getId())
        .owner(o.getOwner())
        .state(o.getState())
        .products(o.getProducts())
        .message(msg)
        .build();
    }
}
