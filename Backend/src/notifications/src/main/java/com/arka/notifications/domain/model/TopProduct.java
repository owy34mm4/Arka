package com.arka.notifications.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopProduct {
    private Long productoId;
    private String nombre;
    private Integer totalVendido;
}
