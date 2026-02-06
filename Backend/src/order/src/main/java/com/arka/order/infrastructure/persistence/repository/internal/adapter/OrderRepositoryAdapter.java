package com.arka.order.infrastructure.persistence.repository.internal.adapter;

import org.springframework.stereotype.Repository;

import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.persistence.mapper.adapter.PersistanceOrderMapper;
import com.arka.order.infrastructure.persistence.repository.internal.gateway.IJPAOrderRepository;
import com.arka.shared.domain.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements IOrderRepository {

    private final IJPAOrderRepository orderRepository;
    private final PersistanceOrderMapper persistanceOrderMapper;

    @Override
    public Order save(Order order) {
       var entity = persistanceOrderMapper.toEntity(order);
       entity = orderRepository.save(entity);
       return persistanceOrderMapper.toDomain(entity);

    }

    @Override
    public Order findById(Long orderId) {
        var entity = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Order "));
        return persistanceOrderMapper.toDomain(entity);
    }
    
}
