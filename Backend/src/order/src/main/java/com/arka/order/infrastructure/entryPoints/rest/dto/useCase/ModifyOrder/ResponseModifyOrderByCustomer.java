package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder;

import java.util.List;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;

import com.arka.shared.application.ports.out.product.ProductInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseModifyOrderByCustomer {
    Long id;
    Long owner_id;
    String owner_username;
    OrderState state;
    List<ProductInfo> products;
    String message;

    public static ResponseModifyOrderByCustomer createFromModel(Order o, String msg){
        return ResponseModifyOrderByCustomer.builder()
        .id(o.getId())
        .owner_id(o.getOwner().getId())
        .owner_username(o.getOwner().getUsername())
        .state(o.getState())
        .products(o.getProducts())
        .message(msg)
        .build();
    }
}
