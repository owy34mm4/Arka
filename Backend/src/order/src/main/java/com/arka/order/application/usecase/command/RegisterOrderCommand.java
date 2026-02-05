package com.arka.order.application.usecase.command;


import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.RequestRegisterOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RegisterOrderCommand {
    Long id;
    Long requesterId;
    Long shopingCartId;
    

    public static RegisterOrderCommand createFromRequest(RequestRegisterOrder request){
        return RegisterOrderCommand.builder()
        .id(null)
        .requesterId(request.getRequester_id())
        .shopingCartId(request.getShoping_cart_id())
        .build();
            
    }

    public Order toModel(){
        return  Order.builder()
            .id(this.id)
            .ownerId(this.requesterId)
            .state(null)
            .productsIds(null)
            .products(null)
        .build();
    }
}
