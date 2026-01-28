package com.arka.order.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class Order {
    Long id;
    Long ownerId;
    List<Long> productsIds;
   
    
}
