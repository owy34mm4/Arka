package com.arka.order.infrastructure.persistence.entity;




import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class OrderDetailTable {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
      
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")  
    // @org.hibernate.annotations.Immutable
    private OrderTable order;  
      
    @Column(name = "product_id")  
    // @org.hibernate.annotations.Immutable
    private Long productId;  
      
}