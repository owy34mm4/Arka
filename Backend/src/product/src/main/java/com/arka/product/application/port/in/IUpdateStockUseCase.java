package com.arka.product.application.port.in;


import com.arka.product.application.useCase.command.UpdateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public interface IUpdateStockUseCase {
    Product execute (UpdateProductCommand cmd) throws InvalidPropertiesGiven;
} 
