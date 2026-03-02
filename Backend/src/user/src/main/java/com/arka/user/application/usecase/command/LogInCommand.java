package com.arka.user.application.usecase.command;

import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.RequestLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInCommand {
    private String username;
    private String password;

    public static LogInCommand createFromRequest(RequestLogin request){
        return LogInCommand.builder()
            .username(request.getUsername())
            .password(request.getPassword())
        .build();

    }
}
