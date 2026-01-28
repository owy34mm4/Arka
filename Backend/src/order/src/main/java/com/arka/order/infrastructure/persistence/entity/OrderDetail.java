package com.arka.order.infrastructure.persistence.entity;




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
@Table(name = "order_detail")  
@Getter @Setter  
@NoArgsConstructor @AllArgsConstructor  
@Builder  
public class OrderDetail {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
      
    @Column(name = "order_id")  
    private Long orderId;  
      
    @Column(name = "product_id")  
    private Long productId;  
      
}