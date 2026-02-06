package com.arka.order.application.port.in;

import com.arka.order.application.usecase.command.ModifyOrderCommand;
import com.arka.order.domain.model.Order;

public interface IModifyOrderUseCase {
    Order execute(ModifyOrderCommand cmd);
}
