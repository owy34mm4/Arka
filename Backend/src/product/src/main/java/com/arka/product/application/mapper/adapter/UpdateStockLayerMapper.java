package com.arka.product.application.mapper.adapter;

import com.arka.product.application.useCase.command.UpdateStockCommand;
import com.arka.product.domain.model.Product;
import com.arka.shared.application.mapper.gateway.ILayerMapper;

import lombok.Builder;

@Builder
public class UpdateStockLayerMapper implements ILayerMapper< com.arka.product.domain.model.Product , UpdateStockCommand > {

    @Override
    public Product toDomain(UpdateStockCommand cmd) {
        return Product.builder()
            .id(cmd.getProductId())
            .stock(cmd.getNewStock())
           
        .build();
    }

    @Override
    public UpdateStockCommand toCommand(Product model) {
        return UpdateStockCommand.builder()
            .productId(model.getId())
            .newStock(model.getStock())
        .build();
    }
    
}
