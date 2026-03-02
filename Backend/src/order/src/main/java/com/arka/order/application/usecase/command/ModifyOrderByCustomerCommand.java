package com.arka.order.application.usecase.command;


import java.util.List;

import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestModifyOrderByCustomer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModifyOrderByCustomerCommand {

    private Long orderId;

    private List<Long> productsIds;
    

    public static ModifyOrderByCustomerCommand createFromRequest(RequestModifyOrderByCustomer request){
        return ModifyOrderByCustomerCommand.builder()
        .orderId(request.getOrder_id())
        .productsIds(request.getProducts_ids())
        .build();
    }

    public Order toDomain(){
        return Order.builder()
        .id(this.getOrderId())
        .ownerId(null)
        .owner(null)
        .state(null)
        .productsIds(this.getProductsIds())
        .products(null)
        .build();
    }
}
