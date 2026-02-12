package com.arka.order.application.port.in;

import com.arka.order.application.usecase.command.ModifyOrderByCustomerCommand;

import com.arka.order.domain.model.Order;

public interface IModifyOrderByCustomerUseCase {
    Order execute(ModifyOrderByCustomerCommand cmd);
}
