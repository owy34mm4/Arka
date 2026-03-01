package com.arka.shared.application.ports.out.order;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

import com.arka.shared.application.ports.out.order.enums.OrderStateInfo;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderInfo {
    Long id;
    Long ownerId;
    UserInfo owner;
    OrderStateInfo state;
    
    List<Long> productsIds;
    List<ProductInfo> products;
    LocalDateTime timeStamp;
   
    Integer subtotalValue;
    String subtotalCurrency;

    Integer totalValue;
    String totalCurrency;
}
