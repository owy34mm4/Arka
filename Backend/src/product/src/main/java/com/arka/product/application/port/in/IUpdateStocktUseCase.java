package com.arka.product.application.port.in;

import com.arka.product.application.useCase.command.UpdateStockCommand;
import com.arka.product.domain.model.Product;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public interface IUpdateStocktUseCase {
    Product execute (UpdateStockCommand cmd) throws InvalidPropertiesGiven;
}
