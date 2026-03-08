package com.arka.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


import com.arka.category.CategoryModuleConfig;
import com.arka.notifications.NotificationsModuleConfig;
import com.arka.order.OrderModuleConfig;
import com.arka.product.ProductModuleConfig;
import com.arka.shared.SharedKernelConfigurationModule;
import com.arka.shopingCart.ShopingCartConfigurationModule;
import com.arka.user.UserModuleConfig;

@SpringBootApplication(scanBasePackages = "com.arka")
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