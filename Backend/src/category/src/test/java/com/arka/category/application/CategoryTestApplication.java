package com.arka.category.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


import com.arka.category.CategoryModuleConfig;

// ✅ Punto de entrada SOLO para tests del módulo category
@SpringBootApplication
@Import({
    CategoryModuleConfig.class
})
public class CategoryTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryTestApplication.class, args);
    }
}