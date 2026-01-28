package com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @Getter @AllArgsConstructor @NoArgsConstructor
public class RequestUpdateStockProduct {
    Long product_id;
    int new_stock;
}
