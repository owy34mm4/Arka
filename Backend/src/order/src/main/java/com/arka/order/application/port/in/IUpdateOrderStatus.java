package com.arka.order.application.port.in;

import com.arka.order.application.usecase.command.UpdateOrderStatusCommand;
import com.arka.order.domain.model.Order;

public interface IUpdateOrderStatus {
    public Order execute(UpdateOrderStatusCommand cmd);
}
