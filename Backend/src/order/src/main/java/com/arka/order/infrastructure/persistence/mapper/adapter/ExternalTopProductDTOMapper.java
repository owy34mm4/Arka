package com.arka.order.infrastructure.persistence.mapper.adapter;

import org.springframework.stereotype.Component;

import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopProductDTO;
import com.arka.shared.application.ports.out.order.dtos.TopProductDTOInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;

@Component
public class ExternalTopProductDTOMapper implements IExternalMapper<TopProductDTOInfo, TopProductDTO> {

    @Override
    public TopProductDTO toDomain(TopProductDTOInfo info) {
        return TopProductDTO.builder()
        .productoId(info.getProductoId())
        .nombre(info.getNombre())
        .totalVendido(info.getTotalVendido())
        .build();
    }

    @Override
    public TopProductDTOInfo toInfo(TopProductDTO domain) {
        return TopProductDTOInfo.builder()
        .productoId(domain.getProductoId())
        .nombre(domain.getNombre())
        .totalVendido(domain.getTotalVendido())
        .build();
    }
    
}
