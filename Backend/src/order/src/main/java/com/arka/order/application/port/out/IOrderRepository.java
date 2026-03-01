package com.arka.order.application.port.out;

import java.util.List;
import java.util.Set;

import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopCustomerWeeklyDTO;
import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopProductDTO;

public interface IOrderRepository {
    boolean existsById(Long id);
    Order save(Order order);
    List<Order> findOrderLast7Days();
    Order findById(Long orderId);
    TopProductDTO topMasVendido();
    List<Order> findAllById(List<Long> ids);
    TopCustomerWeeklyDTO topCustomerOfWeek();
}
