package com.arka.order.infrastructure.persistence.repository.internal.adapter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.persistence.entity.OrderTable;
import com.arka.order.infrastructure.persistence.entity.enums.OrderState;
import com.arka.order.infrastructure.persistence.mapper.adapter.PersistanceOrderMapper;
import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopCustomerWeeklyDTO;
import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopProductDTO;
import com.arka.order.infrastructure.persistence.repository.internal.gateway.IJPAOrderRepository;
import com.arka.shared.domain.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements IOrderRepository {

    private final IJPAOrderRepository orderRepository;
    private final PersistanceOrderMapper persistanceOrderMapper;

    @Override  
    public Order save(Order domain) {  
        // 1. Buscar la entidad conectada (IMPORTANTE para evitar OptimisticLock)  
    OrderTable entity = (domain.getId() != null)   
        ? orderRepository.findById(domain.getId()).orElse(new OrderTable())  
        : new OrderTable();  
  
    // 2. Mapear campos simples  
    entity.setOwnerId(domain.getOwnerId());  
    entity.setPrice(domain.getTotal().getValue());  
    entity.setCurrency(domain.getTotal().getCurrency());  
    entity.setState(com.arka.order.infrastructure.persistence.entity.enums.OrderState.valueOf(domain.getState().name()));  
  
    // 3. Actualizar detalles usando el helper (esto maneja el orphanRemoval correctamente)  
    entity.updateDetails(domain.getProductsIds());  
  
    entity = orderRepository.save(entity); 
        return persistanceOrderMapper.toDomain(entity);  
    }

    @Override
    public Order findById(Long orderId) {
        var entity = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Order "));
        return persistanceOrderMapper.toDomain(entity);
    }

    @Override
    public List<Order> findOrderLast7Days() {
        LocalDateTime hace7Dias= LocalDateTime.now().minusDays(7);
        return orderRepository.getlast7DaysOrders(hace7Dias).stream()
        .map(orderData -> persistanceOrderMapper.toDomain(orderData)).toList();
    }

    @Override
    public TopProductDTO topMasVendido() {
        List<Object[]> result = orderRepository.topMasVendido(LocalDateTime.now().minusDays(7), LocalDateTime.now(),PageRequest.of(0,1));
        if (result.isEmpty()) return null;  
  
        Object[] row = result.get(0);  
        return TopProductDTO.builder()  
            .productoId(((Number) row[0]).longValue())  
            .nombre((String) row[1])  
            .totalVendido(((Number) row[2]).intValue())  
            .build();
    }

    @Override
    public TopCustomerWeeklyDTO topCustomerOfWeek() {
        return orderRepository.topCustomerOfWeek(LocalDateTime.now().minusDays(7), LocalDateTime.now(), PageRequest.of(0, 1)).getContent().stream().findFirst().orElse(null);
    }
    

    @Override
    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public List<Order> findAllById(List<Long> ids) {
        return orderRepository.findAllById(ids).stream()
            .map(order-> persistanceOrderMapper.toDomain(order)).toList();
    }

    

    
}
