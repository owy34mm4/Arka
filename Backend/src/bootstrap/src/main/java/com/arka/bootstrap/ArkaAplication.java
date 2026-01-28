package com.arka.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.arka")
@EntityScan(basePackages = "com.arka")  
@EnableJpaRepositories(basePackages = "com.arka")
public class ArkaAplication {
    public static void main(String[] args) {
		SpringApplication.run(ArkaAplication.class, args);
	}

    
}