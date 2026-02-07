package com.arka.product.infrastructure.persistence.entity;

// import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_history")
public class ProductHistoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private String currency;

    private Integer stock;

    private Date createdAt;

    @Column(name= "createdBy")
    private Long createdById;

    private Date modifiedAt;

    @Column(name = "modifiedBy")
    private Long modifiedById;
    
    @Column(name = "product_id")
    private Long productId;

    // @PrePersist
    // protected void onCreate(){
    //     this.createdAt = Date.from(Instant.now());
    // }
}
