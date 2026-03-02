package com.arka.bootstrap.infrastructure.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;

import lombok.RequiredArgsConstructor;

@Component  
@RequiredArgsConstructor  
public class BCryptPasswordEncoderAdapter implements IPasswordEncoderPort {  
    private final PasswordEncoder passwordEncoder; // El de Spring Security  

    @Override  
    public String encode(String rawPassword) {  
        return passwordEncoder.encode(rawPassword);  
    }  

    @Override  
    public boolean matches(String rawPassword, String encodedPassword) {  
        return passwordEncoder.matches(rawPassword, encodedPassword);  
    }  
}
