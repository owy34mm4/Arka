package com.arka.product.infrastructure.persistence.entity;



import java.util.List;



import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
public class ProductTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

   @ElementCollection
        @CollectionTable(
            name="products_x_categories",
            joinColumns = @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="fk_productsxcategories_product"))
        )
    @Column(name = "category_id")
    private List<Long> categoriesId;

    @ElementCollection
        @CollectionTable(name = "shoping_carts_detail",
            joinColumns = @JoinColumn(name = "product_id")
        )
    @Column(name = "shoping_cart_id")
    private List<Long> shopingCartId;

    @ElementCollection
        @CollectionTable(name = "order_detail",
            joinColumns = @JoinColumn(name = "product_id")
        )
    @Column(name = "order_id")  
    private List<Long> ordersIds;

   
    
}
