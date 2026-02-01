package com.arka.user.application.port.in;

import com.arka.user.application.usecase.command.CreateUserCommand;
import com.arka.user.domain.model.User;

public interface ICreateUserUseCase {
    User execute (CreateUserCommand cmd);
}
