package com.arka.shared.application.ports.out.security;

public interface IPasswordEncoderPort {
    String encode(String rawPassword);  
    boolean matches(String rawPassword, String encodedPassword);
}
