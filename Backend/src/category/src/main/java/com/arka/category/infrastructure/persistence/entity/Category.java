package com.arka.category.infrastructure.persistence.entity;

import java.util.List;

import com.arka.product.infrastructure.persistence.entity.Product;


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
@Table(name="categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
/**  
 * ENTIDAD JPA - Representa la tabla en la base de datos.  
 *   
 * ⚠️ Esta NO es la entidad de dominio.  
 *    Es solo una representación de persistencia.  
 */
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
        @JoinTable(
            name= "products_x_categories",
            joinColumns = @JoinColumn(name="category_id", foreignKey = @ForeignKey(name="fk_productsxcategories_category")),
            inverseJoinColumns = @JoinColumn(name="product_id", foreignKey = @ForeignKey(name="fk_productsxcategories_product"))
        )
    private List<Product> products;
}
