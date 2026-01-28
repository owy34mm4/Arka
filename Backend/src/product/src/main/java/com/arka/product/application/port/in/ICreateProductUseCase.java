package com.arka.product.application.port.in;

import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public interface ICreateProductUseCase {

    Product execute(CreateProductCommand cmd) throws InvalidPropertiesGiven;
    
}
