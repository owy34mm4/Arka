package com.arka.user.application.port.out;

public interface ITokenGenerator {
    String generateToken(String username, String role, Long userId);
}
