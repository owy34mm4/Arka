package com.arka.user.application.port.in;

import com.arka.user.application.usecase.command.LogInCommand;

import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.ResponseLogIn;

public interface ILoginUseCase {
    ResponseLogIn execute (LogInCommand cmd);
}
