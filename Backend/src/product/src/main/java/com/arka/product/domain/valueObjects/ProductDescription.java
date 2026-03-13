package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductDescription {
    private String value;

    public ProductDescription (String value){
        if (value ==null || value.isBlank() || value.length()<=10) {throw new InvalidPropertiesGiven("Product");}

        this.value=value;
    }
}
