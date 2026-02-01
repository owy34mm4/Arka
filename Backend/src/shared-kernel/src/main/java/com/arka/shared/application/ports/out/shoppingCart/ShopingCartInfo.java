package com.arka.shared.application.ports.out.shoppingCart;

import java.util.Date;
import java.util.List;

import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShopingCartInfo {
    private Long id;
    private Date createdAt;
    private Long ownerId;
    private UserInfo owner;
    private List<Long> productsIds;
    private List<ProductInfo> products;
    private boolean isOrdered;
}
