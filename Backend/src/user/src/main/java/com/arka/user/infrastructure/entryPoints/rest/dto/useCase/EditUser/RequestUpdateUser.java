package com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class RequestUpdateUser {
    @NotBlank
    private Long requester_id;

    @NotBlank
    private Long id_to_modify;

    @NotBlank
    private String first_name;

    private String last_name;

    @NotBlank
    private String first_surname;

    private String last_surname;

    @Email
    private String email;

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;

    private String role;
}
