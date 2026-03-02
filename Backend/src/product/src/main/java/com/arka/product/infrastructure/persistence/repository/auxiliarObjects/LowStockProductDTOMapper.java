package com.arka.product.infrastructure.persistence.repository.auxiliarObjects;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.product.dtos.LowStockProductDTOInfo;
import com.arka.shared.infrastructure.persistence.mapper.gateway.IExternalMapper;

@Component  
public class LowStockProductDTOMapper implements IExternalMapper<LowStockProductDTOInfo, LowStockProductDTO>{  
  
    public List<LowStockProductDTO> fromRawRows(List<Object[]> rows) {  
        return rows.stream()  
            .map(r -> LowStockProductDTO.builder()  
                .id(    (Long)    r[0])  
                .name(  (String)  r[1])  
                .currentStock( (Integer) r[2])  
                .build()  
            )  
            .toList();  
    }

    @Override
    public LowStockProductDTO toDomain(LowStockProductDTOInfo info) {
        return LowStockProductDTO.builder()
            .id(info.getId())
            .name(info.getName())
            .currentStock(info.getCurrentStock())
        .build();
    }

    @Override
    public LowStockProductDTOInfo toInfo(LowStockProductDTO domain) {
        return LowStockProductDTOInfo.builder()
            .id(domain.getId())
            .name(domain.getName())
            .currentStock(domain.getCurrentStock())
        .build();
    }  

}
