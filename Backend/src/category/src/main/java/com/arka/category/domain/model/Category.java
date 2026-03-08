package com.arka.category.domain.model;

import java.util.List;

import com.arka.category.domain.valueObject.CategoryName;
import com.arka.shared.application.ports.out.product.ProductInfo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Builder
public class Category {
    private Long id;
    private CategoryName name;
    private List<Long> productsId;
    private List<ProductInfo> products;

    public static Category createCategory(String name){
        return Category.builder()
            .id(null)
            .name(CategoryName.create(name))
            .productsId(null)
            .products(null)
        .build();

    }
    
}
    