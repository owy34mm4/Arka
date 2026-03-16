package com.arka.product.application.port.in;


import com.arka.product.application.useCase.command.UpdateStockProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public interface IUpdateStockUseCase {
    Product execute (UpdateStockProductCommand cmd) throws InvalidPropertiesGiven;
} 
