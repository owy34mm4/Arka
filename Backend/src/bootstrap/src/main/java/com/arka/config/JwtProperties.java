package com.arka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix= "jwt")
@Component
public class JwtProperties {  
  
    private String secret;  
    private long expiration; // en ms o segundos, como decidas  
    private Long userId;
}
