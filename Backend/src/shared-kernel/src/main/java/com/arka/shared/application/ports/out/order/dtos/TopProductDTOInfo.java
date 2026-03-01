package com.arka.shared.application.ports.out.order.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopProductDTOInfo {
    private Long productoId;
    private String nombre;
    private Integer totalVendido;
}
