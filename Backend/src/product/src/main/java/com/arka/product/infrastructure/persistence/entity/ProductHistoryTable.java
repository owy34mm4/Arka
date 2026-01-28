package com.arka.product.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "product_history")
public class ProductHistoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    
    private Date createdAt;

    //private Long modifiedBy; //TO-DO == Link w ClientId
    
    @Column(name = "product_id")
    private Long productId;

    @PrePersist
    protected void onCreate(){
        this.createdAt = Date.from(Instant.now());
    }
}
