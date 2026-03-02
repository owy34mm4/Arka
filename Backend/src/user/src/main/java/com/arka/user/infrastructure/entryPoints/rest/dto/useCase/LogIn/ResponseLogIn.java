package com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseLogIn {
    private String token;
    private String username;
    private String role;
    private Long userId;
}
