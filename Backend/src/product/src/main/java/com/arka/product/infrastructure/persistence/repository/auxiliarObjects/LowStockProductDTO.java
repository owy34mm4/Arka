package com.arka.product.infrastructure.persistence.repository.auxiliarObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter  
@NoArgsConstructor @AllArgsConstructor  
@Builder
public class LowStockProductDTO {
   private Long  id;  
    private String name;  
    private Integer currentStock;
}
