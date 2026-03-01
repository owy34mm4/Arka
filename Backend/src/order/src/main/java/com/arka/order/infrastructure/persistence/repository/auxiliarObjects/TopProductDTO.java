package com.arka.order.infrastructure.persistence.repository.auxiliarObjects;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TopProductDTO {
    private Long productoId;
    private String nombre;
    private Integer totalVendido;
}
