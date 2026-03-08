package com.arka.product;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.arka.product")
@EnableJpaRepositories(basePackages = "com.arka.product")
@EntityScan(basePackages = "com.arka.product")  
public class ProductModuleConfig {
    
}
