package com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock.mapper;

import com.arka.product.application.useCase.command.UpdateStockCommand;
import com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock.RequestUpdateStockProduct;
import com.arka.shared.infrastructure.api.mapper.gateway.IApiMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder @AllArgsConstructor
public class ApiMapperUpdateStockProduct implements IApiMapper<UpdateStockCommand, RequestUpdateStockProduct>{

    @Override
    public UpdateStockCommand toCommand(RequestUpdateStockProduct request) {
        return UpdateStockCommand.builder()
            .newStock(request.getNew_stock())
        .build();
    }
    
}
