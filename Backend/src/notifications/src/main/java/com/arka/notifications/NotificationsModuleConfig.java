package com.arka.notifications;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  
  
@Configuration  
@ComponentScan(basePackages = "com.arka.notifications")  
@EntityScan(basePackages = "com.arka.notifications") 
public class NotificationsModuleConfig {  
}
