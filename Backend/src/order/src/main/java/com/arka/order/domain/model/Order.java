package com.arka.order.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.order.domain.valueObjects.OrderSubtotal;
import com.arka.order.domain.valueObjects.OrderTotal;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Long id;
    Long ownerId;
    UserInfo owner;
    OrderState state;
    List<Long> productsIds;
    List<ProductInfo> products;
    LocalDateTime timeStamp;
    OrderSubtotal subtotal;
    OrderTotal total;


    public void updateProductsIds(List<Long> newProductsIds) {    
        if (this.productsIds == null) this.productsIds = new java.util.ArrayList<>();  
    this.productsIds.clear();    
    if (newProductsIds != null) {  
        this.productsIds.addAll(newProductsIds);    
    }
    }
    

    

}
