package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseUpdateOrderStatus {
    private Long order_id;
    private OrderState order_status;
    private String message;

    public static ResponseUpdateOrderStatus createFromModel(Order useCaseReturn, String msg){
        return ResponseUpdateOrderStatus.builder()
        .order_id(useCaseReturn.getId())
        .order_status(useCaseReturn.getState())
        .message(msg)
        .build();
        
    };
}
