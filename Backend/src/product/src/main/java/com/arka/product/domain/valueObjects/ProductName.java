package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductName {

    String value;

    public ProductName (String value){
        if (value.isBlank() || value ==null) {throw new InvalidPropertiesGiven("Product"); }
        this.value = value;
    }
    
}
