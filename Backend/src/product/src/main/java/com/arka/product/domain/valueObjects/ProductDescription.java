package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductDescription {
    String value;

    public ProductDescription (String value){
        if (value.isBlank() || value.length()<5) {throw new InvalidPropertiesGiven("Product");}

        this.value=value;
    }
}
