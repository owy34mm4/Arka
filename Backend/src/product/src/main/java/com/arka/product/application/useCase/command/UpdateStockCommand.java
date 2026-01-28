package com.arka.product.application.useCase.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Long productId
--
 * int newStock
 */
@Builder @Getter @Setter
public class UpdateStockCommand {
    Long productId;
    int newStock;
}
