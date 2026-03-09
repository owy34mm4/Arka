package com.arka.order;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan( basePackages = "com.arka.order")
@EntityScan(basePackages = "com.arka.order") 
@EnableJpaRepositories(basePackages = "com.arka.order")
public class OrderModuleConfig {
    
}
