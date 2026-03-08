package com.arka.shopingCart;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.arka.shopingCart")
@EnableJpaRepositories(basePackages = "com.arka.shopingCart")
@EntityScan(basePackages = "com.arka.shopingCart")  
public class ShopingCartConfigurationModule {
    
}
