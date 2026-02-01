package com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser;

import com.arka.user.domain.model.User;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseCreateUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String firstSurname;
    private String lastSurname;
    private String username;
    private String password;    
    private boolean isActive;
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
            .message(msg)

        .build();
    }
}
