package com.arka.notifications.application.port.in;

import com.arka.notifications.application.useCase.command.GetOrphanCartsCommand;

public interface IGetOrphanCartsUseCase {

    public void execute (GetOrphanCartsCommand cmd);

    
} 
