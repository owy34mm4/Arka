package com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser;

import com.arka.user.domain.model.User;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.ResponseCreateUser;

import lombok.Getter;

@Getter
public class ResponseUpdateUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String firstSurname;
    private String lastSurname;
    private String username;
    private String password;    
    private boolean isActive;
    private String role;
    private String message;

    public static ResponseCreateUser createFromModel(User model, String msg){
        return ResponseCreateUser.builder()
            .id(model.getId())
            .firstName(model.getFirstName())
            .lastName(model.getLastName())
            .firstSurname(model.getFirstSurname())
            .lastSurname(model.getLastSurname())
            .username(model.getUsername())
            .password(model.getPassword())
            .isActive(model.isActive())
            .role(model.getRole().toString())
            .message(msg)

        .build();
    }
}
