package com.arka.user.application.port.in;

import com.arka.user.application.usecase.command.UpdateUserCommand;
import com.arka.user.domain.model.User;

public interface IUpdateUserUsecase {
    User execute (UpdateUserCommand cmd);
}
