package com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart;

import java.util.List;

import lombok.Getter;

@Getter
public class RequestSaveCart {
    Long owner_id;
    List<Long> products_ids;
}
