package com.arka.user.domain.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.user.domain.model.enums.Role;
import com.arka.user.domain.valueObjects.UserName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor 
@Builder
public class User {
    private Long id;
    private UserName name;
    private String email;
    private String username;
    private String password;    
    private boolean isActive;
    private Date lastLogin;
    private LocalDateTime createdAt;
    private Role role;
    private List<Long> shoppingCartsIds;  
    private List<Long> ordersIds;
    private List<ShopingCartInfo> shopingCarts;

    public static User createUser(String firstName, String lastName, String firstSurname, String lastSurname,
                                 String email, String username, String password, Role rol){
        return User.builder()
        .id(null)
        .name(UserName.create(firstName, lastName, firstSurname, lastSurname))
        .email(email)
        .username(username)
        .password(password)
        .role(rol)
        .createdAt(LocalDateTime.now())
        .isActive(true)
        .build();
    }

    public void editUserInstance(User newData){
        this.setId(newData.getId());
        this.setName(newData.getName());
        this.setEmail(newData.getEmail());
        this.setUsername(newData.getUsername());
        this.setPassword(newData.getPassword());
    }



}
