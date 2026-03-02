package com.arka.notifications.application.port.in;

import com.arka.notifications.application.useCase.command.GetRestockProductsCommand;

public interface IGetRestockProductsUseCase {

    public void execute (GetRestockProductsCommand cmd);
    
} 
