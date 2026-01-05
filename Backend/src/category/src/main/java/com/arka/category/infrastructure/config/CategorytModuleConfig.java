package com.arka.category.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**  
 * Configuración específica del módulo Category.  
 */  
@Configuration  
@ComponentScan(basePackages = "com.arka.category")  
@EnableJpaRepositories(basePackages = "com.arka.category.infrastructure.persistence.repository")  
public class CategorytModuleConfig {  
    // Configuraciones específicas del módulo  
}