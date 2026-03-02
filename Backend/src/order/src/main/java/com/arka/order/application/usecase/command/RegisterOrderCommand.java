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
    Long shopingCartId;
    

    public static RegisterOrderCommand createFromRequest(RequestRegisterOrder request){
        return RegisterOrderCommand.builder()
        .id(null)
        .shopingCartId(request.getShoping_cart_id())
        .build();
            
    }

    public Order toModel(){
        return  Order.builder()
            .id(this.id)
            .ownerId(null)
            .state(null)
            .productsIds(null)
            .products(null)
        .build();
    }
}
