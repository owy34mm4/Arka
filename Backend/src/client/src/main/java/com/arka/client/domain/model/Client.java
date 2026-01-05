package com.arka.client.domain.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.arka.order.domain.model.Order;
import com.arka.shopingCart.domain.model.ShopingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor 
@Builder
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String firstSurname;
    private String lastSurname;
    private String username;
    private String password;    
    private boolean isActive;
    private Date lastLogin;
    private LocalDateTime createdAt;
    private List<ShopingCart> shoppingCarts;  
    private List<Order> orders;

}
