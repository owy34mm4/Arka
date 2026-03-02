package com.arka.bootstrap.infrastructure.security;

import org.springframework.stereotype.Component;

import com.arka.user.application.port.out.ITokenGenerator;

import lombok.RequiredArgsConstructor;

@Component  
@RequiredArgsConstructor  
public class TokenGeneratorAdapter implements ITokenGenerator {  
  
    private final JwtAdapter jwtAdapter;  
  
    @Override  
    public String generateToken(String username, String role, Long userId) {  
        return jwtAdapter.generateToken(username, role, userId);  
    }  
}
