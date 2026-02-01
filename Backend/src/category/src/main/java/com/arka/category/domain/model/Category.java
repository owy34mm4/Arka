package com.arka.category.domain.model;

import java.util.List;

import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Builder
public class Category {
    private Long id;
    private String name;
    private List<Long> productsId;
    private List<ProductInfo> products;

    public static Category create(Long id, String name, List<Long> products) throws InvalidPropertiesGiven {
        validateName(name);
        

        return Category.builder()
                        .id(id)
                        .name(name)
                        .productsId(products)
                        .products(null)
                .build();

    }

    

    private static void validateName(String nameToCheck) throws InvalidPropertiesGiven{
        if(nameToCheck.strip().isBlank() || nameToCheck ==null) throw new InvalidPropertiesGiven();
    }

    
}
    