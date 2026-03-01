package com.arka.order.infrastructure.persistence.repository.external.adapter;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;


import com.arka.order.infrastructure.persistence.mapper.adapter.ExternalOrderMapper;
import com.arka.order.infrastructure.persistence.mapper.adapter.ExternalTopCustomerWeeklyMapper;
import com.arka.order.infrastructure.persistence.mapper.adapter.ExternalTopProductDTOMapper;
import com.arka.order.infrastructure.persistence.repository.internal.adapter.OrderRepositoryAdapter;

import com.arka.shared.application.ports.out.order.IOrderDataPort;
import com.arka.shared.application.ports.out.order.OrderInfo;
import com.arka.shared.application.ports.out.order.dtos.TopCustomerWeeklyDTOInfo;
import com.arka.shared.application.ports.out.order.dtos.TopProductDTOInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExternalOrderRepositoryAdapter implements IOrderDataPort {

    private final OrderRepositoryAdapter innerRepository;
    private final ExternalOrderMapper externalOrderMapper;
    private final ExternalTopProductDTOMapper externalTopProductMapper;
    private final ExternalTopCustomerWeeklyMapper externalTopCustomerMapper;


    @Override
    public boolean existsById(Long Id) {
        return innerRepository.existsById(Id);
    }

    public List<OrderInfo> findOrderLast7Days(){
        return innerRepository.findOrderLast7Days().stream()       
            .map(order-> externalOrderMapper.toInfo(order)).toList();

    }

    @Override
    public List<OrderInfo> findAllById(List<Long> ordersIds) {
        return innerRepository.findAllById(ordersIds).stream()
            .map(orderDomain-> externalOrderMapper.toInfo(orderDomain))
            .toList();
    }

    @Override
    public OrderInfo findById(Long Id) {
        return externalOrderMapper.toInfo(innerRepository.findById(Id));
    }

    @Override
    public OrderInfo save(OrderInfo data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public TopProductDTOInfo topMasVendido() {
        return externalTopProductMapper.toInfo(innerRepository.topMasVendido());
    }

    @Override
    public TopCustomerWeeklyDTOInfo topCustomerOfWeek() {
        return externalTopCustomerMapper.toInfo(innerRepository.topCustomerOfWeek());
    }

    
}
