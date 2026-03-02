package com.arka.shared.application.ports.out.product.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter  
@NoArgsConstructor @AllArgsConstructor  
@Builder
public class LowStockProductDTOInfo {
    private Long  id;  
    private String name;  
    private Integer currentStock;
}
