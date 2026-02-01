package com.arka.category.infrastructure.persistence.entity;

import java.util.List;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder

public class CategoryTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //OLD WAY -> UNCOMPATIBLE W UN-PLUGGED ARCH
    //@ManyToMany
    //    @JoinTable(
    //        name= "products_x_categories",
    //        joinColumns = @JoinColumn(name="category_id", foreignKey = @ForeignKey(name="fk_productsxcategories_category")),
    //        inverseJoinColumns = @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="fk_productsxcategories_product"))
    //    )
    //private List<Product> products;

    @ElementCollection
    @CollectionTable(
        name = "products_x_categories",
        joinColumns = @JoinColumn(name="category_id")
    )
    @Column(name = "product_id")
    private List<Long> products;
}
