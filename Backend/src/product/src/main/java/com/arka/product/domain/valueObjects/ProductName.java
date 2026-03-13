package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductName {

    private final String value;

    public ProductName (String value){
        if (value ==null || value.isBlank() ) {throw new InvalidPropertiesGiven("Product"); }
        this.value = value;
    }
    
}
