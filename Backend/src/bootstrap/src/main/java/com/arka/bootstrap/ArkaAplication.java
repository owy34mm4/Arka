package com.arka.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.arka.category.CategoryModuleConfig;
import com.arka.notifications.NotificationsModuleConfig;
import com.arka.order.OrderModuleConfig;
import com.arka.product.ProductModuleConfig;
import com.arka.shared.SharedKernelConfigurationModule;
import com.arka.shopingCart.ShopingCartConfigurationModule;
import com.arka.user.UserModuleConfig;

@SpringBootApplication(scanBasePackages = "com.arka")
@EntityScan(basePackages = "com.arka")  
@EnableJpaRepositories(basePackages = "com.arka")
@ComponentScan(basePackages = {
	"com.arka",
	"com.arka.notifications"

} )

@Import({
	NotificationsModuleConfig.class,
	OrderModuleConfig.class,
	CategoryModuleConfig.class,
	ProductModuleConfig.class,
	SharedKernelConfigurationModule.class,
	ShopingCartConfigurationModule.class,
	UserModuleConfig.class

})

public class ArkaAplication {
    public static void main(String[] args) {
		SpringApplication.run(ArkaAplication.class, args);
	}

    
}