package com.arka.order.infrastructure.persistence.entity;


import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.arka.order.infrastructure.persistence.entity.enums.OrderState;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "owner_id" , foreignKey= @ForeignKey(name="fk_order_client"))
    
    @Column(name = "owner_id")
    private Long ownerId;

    @ElementCollection
        @CollectionTable(name = "order_detail",
            joinColumns = @JoinColumn(name = "order_id")
        )
    @Column(name = "product_id")  
    private List<Long> productsIds;

    @Column(name = "created_at")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OrderState state;

    private String currency;

    private Integer price;


    @PrePersist
    private void onCreate(){
        this.createdAt = Date.from(Instant.now());
        this.state = OrderState.PENDIENTE;
    }
}