package com.arka.user.domain.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.user.domain.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor 
@Builder
public class User {
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
    private Role role;
    private List<Long> shoppingCartsIds;  
    private List<Long> ordersIds;
    private List<ShopingCartInfo> shopingCarts;

}
