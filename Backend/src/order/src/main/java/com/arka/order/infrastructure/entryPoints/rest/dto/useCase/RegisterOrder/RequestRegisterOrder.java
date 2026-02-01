package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder;



import lombok.Getter;

@Getter
public class RequestRegisterOrder {
    Long requester_id;
    Long shoping_cart_id;
}
