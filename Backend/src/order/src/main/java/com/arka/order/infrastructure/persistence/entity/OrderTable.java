package com.arka.order.infrastructure.persistence.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.arka.order.infrastructure.persistence.entity.enums.OrderState;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
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

    // @ElementCollection
    //     @CollectionTable(name = "order_detail",
    //         joinColumns = @JoinColumn(name = "order_id")
    //     )
    // @Column(name = "product_id") 
     
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderDetailTable> details = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OrderState state;

    private String currency;

    private Integer price;


    @PrePersist
    private void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.state = OrderState.PENDIENTE;
    }

    // Helper para actualizar detalles sin wipear la tabla
    public void updateDetails(List<Long> newProductIds) {    
        this.details.clear();  
        if (newProductIds != null) {  
            newProductIds.forEach(pid ->  
                this.details.add(OrderDetailTable.builder()  
                    .order(this)  
                    .productId(pid)  
                    .build())  
            );  
        }
    }
}