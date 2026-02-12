package com.arka.order.domain.model;

import java.util.Date;
import java.util.List;

import com.arka.order.domain.model.enums.OrderState;
import com.arka.order.domain.valueObjects.OrderSubtotal;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    Long id;
    Long ownerId;
    UserInfo owner;
    OrderState state;
    List<Long> productsIds;
    List<ProductInfo> products;
    Date timeStamp;
    OrderSubtotal subtotal;
    

}
