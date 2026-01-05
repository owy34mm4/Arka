package com.arka.order.infrastructure.persistence.entity;

import java.util.List;

import com.arka.client.infrastructure.persistence.entity.Client;
import com.arka.product.infrastructure.persistence.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id" , foreignKey= @ForeignKey(name="fk_order_client"))
    private Client owner;

    @ManyToMany
        @JoinTable(
            name = "order_detail",
            joinColumns = @JoinColumn(name= "order_id", foreignKey = @ForeignKey(name="fk_orderdetail_order")),
            inverseJoinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name="fk_cartdetail_product"))
        )
    private List<Product> products; 
}