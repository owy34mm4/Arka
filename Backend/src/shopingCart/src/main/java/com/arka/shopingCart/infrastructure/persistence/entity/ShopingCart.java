package com.arka.shopingCart.infrastructure.persistence.entity;


import java.util.Date;
import java.util.List;

import com.arka.client.infrastructure.persistence.entity.*;
import com.arka.product.infrastructure.persistence.entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shoping_cart")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ShopingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name = "fk_cart_client"))
    private Client owner;
    
    @ManyToMany
        @JoinTable(
            name = "shoping_cart_detail",
            joinColumns = @JoinColumn(name="shoping_cart_id", foreignKey = @ForeignKey(name="fk_cartdetail_cart")),
            inverseJoinColumns = @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="fk_cartdetail_product"))
        )
    private List<Product> products;
}
