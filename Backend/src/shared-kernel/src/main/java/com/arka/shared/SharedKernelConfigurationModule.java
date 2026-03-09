package com.arka.shared;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.arka.shared")
@EntityScan(basePackages = "com.arka.shared") 
public class SharedKernelConfigurationModule {
    
}
