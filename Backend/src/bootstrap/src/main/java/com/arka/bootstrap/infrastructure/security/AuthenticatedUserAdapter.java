package com.arka.bootstrap.infrastructure.security;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;


@Component  
public class AuthenticatedUserAdapter implements IAuthenticateUserPort {  
  
    @Override  
    public String getUsername() {  
        return getAuthentication().getName();  
    }  
  
    @Override  
    public String getRole() {  
        return getAuthentication()  
            .getAuthorities()  
            .stream()  
            .findFirst()  
            .map(a -> a.getAuthority())  
            .orElse(null);  
    }  
  
    @Override  
    public Long getUserId() {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        AuthenticatedUserDetails details = (AuthenticatedUserDetails) auth.getDetails();  
        return details.getUserId();  
    } 
  
    private Authentication getAuthentication() {  
        return SecurityContextHolder.getContext().getAuthentication();  
    }  
}
