package com.arka.user.application.usecase.command;

import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;

import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser.RequestUpdateUser;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserCommand {
    private Long requesterId;

    private Long idToModify;

    private String first_name;
    private String last_name;
    private String first_surname;
    private String last_surname;
    private String email;
    private String username;
    private String password;
    private Role role;

    public static UpdateUserCommand createFromRequest(RequestUpdateUser request){
        return UpdateUserCommand.builder()
        .requesterId(request.getRequester_id())
        .idToModify(request.getId_to_modify())
        .first_name(request.getFirst_name())
        .last_name(request.getLast_name())
        .first_surname(request.getFirst_surname())
        .last_surname(request.getLast_surname())
        .email(request.getEmail())
        .username(request.getUsername())
        .password(request.getPassword())
        .role(Role.valueOf(request.getRole()))
        .build();
    }

    public User toModel(){
        return User.builder()
        .id(this.getIdToModify())
        .firstName(this.getFirst_name())
        .lastName(this.getLast_name())
        .firstSurname(this.getFirst_surname())
        .lastSurname(this.getLast_name())
        .email(this.email)
        .username(this.getUsername())
        .password(this.getPassword())
        .role(this.getRole())
        .build();
    }
}
