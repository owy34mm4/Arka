package com.arka.shopingCart.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  
@Table(name = "shoping_carts_detail")  
@Getter @Setter  
@NoArgsConstructor @AllArgsConstructor  
@Builder  
public class ShopingCartDetailTable {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
      
    @Column(name = "shoping_cart_id")  
    private Long shopingCartId;  
      
    @Column(name = "product_id")  
    private Long productId;  
      
}
