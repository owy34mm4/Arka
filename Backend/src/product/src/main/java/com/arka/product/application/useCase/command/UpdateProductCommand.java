package com.arka.product.application.useCase.command;

import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.RequestUpdateStockProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class UpdateProductCommand {
    Long productId;
    Integer newStock;

    public static UpdateProductCommand createFromRequest(RequestUpdateStockProduct request){
        return UpdateProductCommand.builder()
            .productId(request.getProduct_id())
            .newStock(request.getNew_stock())
            .build();

    }
}
