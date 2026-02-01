package com.arka.user.application.usecase.command;

import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.RequestCreateUser;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserCommand {
    private Long id;
    private String first_name;
    private String last_name;
    private String first_surname;
    private String last_surname;
    private String username;
    private String password;
    private Role role;

    public static CreateUserCommand createFromRequest(RequestCreateUser request){
        return CreateUserCommand.builder()
        .id(null)
        .first_name(request.getFirst_name())
        .last_name(request.getLast_name())
        .first_surname(request.getFirst_surname())
        .last_surname(request.getLast_surname())
        .username(request.getUsername())
        .password(request.getPassword())
        .role(Role.Administrador)
        .build();
    }

    public User toModel(){
        return User.builder()
        .id(this.getId())
        .firstName(this.getFirst_name())
        .lastName(this.getLast_name())
        .firstSurname(this.getFirst_surname())
        .lastSurname(this.getLast_name())
        .username(this.getUsername())
        .password(this.getPassword())
        .role(this.getRole())
        .build();
    }
}
