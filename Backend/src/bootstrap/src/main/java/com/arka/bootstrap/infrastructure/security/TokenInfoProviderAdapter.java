package com.arka.bootstrap.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.arka.shared.application.ports.in.ITokenInfoProvider;

public class TokenInfoProviderAdapter implements ITokenInfoProvider {

    @Override  
    public String getCurrentUsername() {  
        return SecurityContextHolder.getContext()  
            .getAuthentication()  
            .getName();  
    }  
  
    @Override  
    public String getCurrentRole() {  
        return SecurityContextHolder.getContext()  
            .getAuthentication()  
            .getAuthorities()  
            .stream()  
            .findFirst()  
            .map(GrantedAuthority::getAuthority)  
            .orElse(null);  
    }
    
}
