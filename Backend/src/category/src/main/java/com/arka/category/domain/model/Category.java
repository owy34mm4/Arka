package com.arka.category.domain.model;

import java.util.List;



import com.arka.category.domain.exception.InvalidIDException;
import com.arka.category.domain.exception.InvalidNameException;
import com.arka.product.domain.model.Product;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Builder
public class Category {
    private Long id;
    private String name;
    private List<Product> products;

    public Category create(Long id, String name, List<Product> products) throws InvalidNameException, InvalidIDException{
        validateName();
        validateId();

        return Category.builder()
                        .id(id)
                        .name(name)
                        .products(products)
                .build();

    }

    private void validateName() throws InvalidNameException{
        if(name.strip().isBlank() || name ==null) throw new InvalidNameException();
    }

    private void validateId() throws InvalidIDException{
        if(id<=0 || id == null) throw new InvalidIDException();
    }
}
    