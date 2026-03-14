package com.arka.product.application.useCase.command;

import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.RequestUpdateStockProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class UpdateStockProductCommand {
    Long productId;
    Integer newStock;

    public static UpdateStockProductCommand createFromRequest(RequestUpdateStockProduct request){
        return UpdateStockProductCommand.builder()
            .productId(request.getProduct_id())
            .newStock(request.getNew_stock())
            .build();

    }
}
