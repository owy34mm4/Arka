package com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestSaveCart {
    Long owner_id;
    List<Long> products_ids;
}
