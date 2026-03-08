package com.arka.category;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.arka.category")
@EnableJpaRepositories(basePackages = "com.arka.category")
@EntityScan(basePackages = "com.arka.category")  

public class CategoryModuleConfig {
    
}
