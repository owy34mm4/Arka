package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder;

import com.arka.order.infrastructure.persistence.entity.enums.OrderState;

import lombok.Getter;

@Getter
public class RequestUpdateOrderStatus {
    private Long order_id;
    private Long owner_id;
    private Long requester_id;   
    private OrderState state; 
}
