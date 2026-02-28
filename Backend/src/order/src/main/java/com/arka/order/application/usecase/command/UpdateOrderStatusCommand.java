package com.arka.order.application.usecase.command;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestUpdateOrderStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateOrderStatusCommand {
    private Long orderId;
    private Long ownerId;    
    private Long requesterId;
    private OrderState orderStatus;
    

    public static UpdateOrderStatusCommand createFromRequest(RequestUpdateOrderStatus request){
        return UpdateOrderStatusCommand.builder()
        .orderId(request.getOrder_id())
        .ownerId(request.getOwner_id())
        .requesterId(request.getRequester_id())
        .orderStatus(OrderState.valueOf(request.getState().name()))
        .build();
    }

    public Order toModel(){
        return Order.builder()
        .id(this.orderId)
        .ownerId(this.ownerId)
        .state(this.orderStatus)
        .build();
    }
}
