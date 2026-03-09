package com.arka.user.appliaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.arka.user.UserModuleConfig;

// ✅ Punto de entrada SOLO para tests del módulo user
@SpringBootApplication
@Import({
    UserModuleConfig.class
})
public class UserTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserTestApplication.class, args);
    }
}