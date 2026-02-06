package com.arka.order.application.port.out;

import com.arka.order.domain.model.Order;

public interface IOrderRepository {
    Order save(Order order);

    Order findById(Long orderId);
}
