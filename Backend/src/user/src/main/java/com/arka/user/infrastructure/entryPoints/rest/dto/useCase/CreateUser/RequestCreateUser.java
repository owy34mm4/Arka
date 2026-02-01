package com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateUser {
    private String first_name;
    private String last_name;
    private String first_surname;
    private String last_surname;
    private String username;
    private String password;

    
}
