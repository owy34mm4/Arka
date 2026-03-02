package com.arka.bootstrap.infrastructure.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticatedUserDetails {
    private final String username;  
    private final String role;  
    private final Long userId; // o Long


        public String getUsername() { return username; }  
        public String getRole() { return role; }  
        public Long getUserId() { return userId; }
}
