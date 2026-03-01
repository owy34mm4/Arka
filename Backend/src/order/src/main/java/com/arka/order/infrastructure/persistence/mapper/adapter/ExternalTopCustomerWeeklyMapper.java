package com.arka.order.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopCustomerWeeklyDTO;
import com.arka.shared.application.ports.out.order.dtos.TopCustomerWeeklyDTOInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;

@Component
public class ExternalTopCustomerWeeklyMapper implements IExternalMapper<TopCustomerWeeklyDTOInfo,TopCustomerWeeklyDTO> {

    @Override
    public TopCustomerWeeklyDTO toDomain(TopCustomerWeeklyDTOInfo info) {
        return TopCustomerWeeklyDTO.builder()
            .customerId(info.getCustomerId())
            .firstName(info.getFirstName())
            .firstSurname(info.getFirstSurname())
            .lastName(info.getLastName())
            .lastSurname(info.getLastSurname())
            .ordersCount(info.getOrdersCount())
            .totalSpent(info.getTotalSpent())
        .build();
    }

    @Override
    public TopCustomerWeeklyDTOInfo toInfo(TopCustomerWeeklyDTO domain) {
        return TopCustomerWeeklyDTOInfo.builder()
            .customerId(domain.getCustomerId())
            .firstName(domain.getFirstName())
            .firstSurname(domain.getFirstSurname())
            .lastName(domain.getLastName())
            .lastSurname(domain.getLastSurname())
            .ordersCount(domain.getOrdersCount())
            .totalSpent(domain.getTotalSpent())
        .build();
    }
    
}
