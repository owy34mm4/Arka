package com.arka.order.infrastructure.persistence.mapper.adapter;


import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.order.domain.valueObjects.OrderSubtotal;
import com.arka.order.domain.valueObjects.OrderTotal;
import com.arka.order.infrastructure.persistence.entity.OrderTable;

@Component
public class PersistanceOrderMapper {
    
    public Order toDomain(OrderTable o){
        if (o==null) return null;
        return  Order.builder()
                .id(o.getId())
                .ownerId(o.getOwnerId())
                .state(OrderState.valueOf(o.getState().name()))
                .productsIds(new ArrayList<>(o.getProductsIds()))
                .subtotal(new OrderSubtotal(o.getCurrency(), o.getPrice()))
                .total(new OrderTotal(o.getCurrency(), o.getPrice()))
                .build();
    }

    public OrderTable toEntity (Order o){
        if (o==null) return null;
        return OrderTable.builder()
        .id(o.getId())
        .ownerId(o.getOwnerId())
        .state(com.arka.order.infrastructure.persistence.entity.enums.OrderState.valueOf(o.getState().name()))
        .currency(o.getSubtotal().getCurrency())
        .price(o.getSubtotal().getValue())
        .productsIds(new ArrayList<>(o.getProductsIds()))
        .build();

    }
}
