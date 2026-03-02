package com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn;

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
public class RequestLogin {
    private String username;
    private String password;
    
}
