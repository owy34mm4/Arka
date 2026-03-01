package com.arka.order.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.order.domain.valueObjects.OrderSubtotal;
import com.arka.order.domain.valueObjects.OrderTotal;
import com.arka.shared.application.ports.out.order.OrderInfo;
import com.arka.shared.application.ports.out.order.enums.OrderStateInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;

@Component
public class ExternalOrderMapper implements IExternalMapper<OrderInfo,Order>{

    @Override
    public Order toDomain(OrderInfo info) {
        return Order.builder()
        .id(info.getId())
        .ownerId(info.getOwnerId())
        .owner(info.getOwner())
        .productsIds(info.getProductsIds())
        .products(info.getProducts())
        .timeStamp(info.getTimeStamp())
        .state(OrderState.valueOf(info.getState().name()))
        .subtotal(new OrderSubtotal(info.getTotalCurrency(), info.getTotalValue()))
        .total(new OrderTotal(info.getTotalCurrency(), info.getTotalValue()))
        .build();
    }

    @Override
    public OrderInfo toInfo(Order domain) {
        return OrderInfo.builder()
        .id(domain.getId())
        .ownerId(domain.getOwnerId())
        .owner(domain.getOwner())
        .productsIds(domain.getProductsIds())
        .products(domain.getProducts())
        .timeStamp(domain.getTimeStamp())
        .state(OrderStateInfo.valueOf(domain.getState().name()))
        .subtotalValue(domain.getSubtotal().getValue())
        .subtotalCurrency(domain.getSubtotal().getCurrency())
        .totalValue(domain.getTotal().getValue())
        .totalCurrency(domain.getTotal().getCurrency())
        .build();
    }

 
    
}

