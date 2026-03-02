package com.arka.shared.application.ports.out.security;

public interface IAuthenticateUserPort {
    String getUsername();  
    String getRole();  
    Long getUserId();
}
