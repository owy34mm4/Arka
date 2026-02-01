package com.arka.order.application.port.in;

import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.domain.model.Order;

public interface IRegisterOrderUseCase {
    
    Order execute (RegisterOrderCommand cmd);
}
