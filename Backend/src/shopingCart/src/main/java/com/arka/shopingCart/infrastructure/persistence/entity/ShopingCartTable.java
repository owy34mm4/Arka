package com.arka.shopingCart.infrastructure.persistence.entity;


import java.time.Instant;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shoping_carts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ShopingCartTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "owner_id")
    private Long ownerId;

    @ElementCollection
        @CollectionTable(name = "shoping_carts_detail",
            joinColumns = @JoinColumn(name ="shoping_cart_id")
        )
    @Column(name = "product_id")
    private List<Long> productsIds;

    @Column (name = "is_ordered")
    private boolean isOrdered;


    @PrePersist
    private void onCreate(){
        this.isOrdered=false;
        this.createdAt=Date.from(Instant.now());
    }
    
    
}
