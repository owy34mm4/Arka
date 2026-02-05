package com.arka.shared.application.ports.out.user;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private String firstSurname;
    private String lastSurname;
    private String email;
    private String username;
    private String password;    
    private boolean isActive;
    private Date lastLogin;
    private LocalDateTime createdAt;
    private Roleinfo role;
    private List<Long> shoppingCartsIds;  
    private List<Long> ordersIds;
    private List<ShopingCartInfo> shopingCarts;

}
