package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestRegisterOrder {
    Long shoping_cart_id;
    private long x;
}
