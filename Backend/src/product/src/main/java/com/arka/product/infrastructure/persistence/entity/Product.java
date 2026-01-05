package com.arka.product.infrastructure.persistence.entity;

import com.arka.order.infrastructure.persistence.entity.Order;
import com.arka.shopingCart.infrastructure.persistence.entity.*;

import com.arka.category.infrastructure.persistence.entity.Category;


import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    @ManyToMany
        @JoinTable(
            name="products_x_categories",
            joinColumns = @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="fk_productsxcategories_product")),
            inverseJoinColumns =  @JoinColumn(name="category_id", foreignKey = @ForeignKey(name="fk_productsxcategories_category"))
        )
    private List<Category> categories;

    @ManyToMany
        @JoinTable(
            name = "shoping_cart_detail",
            joinColumns = @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="fk_cartdetail_product")),
            inverseJoinColumns = @JoinColumn(name="shoping_cart_id", foreignKey = @ForeignKey(name="fk_cartdetail_cart"))
        )
    private List<ShopingCart> shoppingCarts;  
      
    @ManyToMany
        @JoinTable(
            name = "order_detail",
            joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name="fk_cartdetail_product")),
            inverseJoinColumns = @JoinColumn(name= "order_id", foreignKey = @ForeignKey(name="fk_orderdetail_order"))
        )
    private List<Order> orders;
}
