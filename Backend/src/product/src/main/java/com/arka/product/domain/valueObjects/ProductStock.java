package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductStock {
    Integer value;

    public ProductStock(Integer value){
        if (value<0 || value ==null) {throw new InvalidPropertiesGiven("Product"); }
        this.value=value;
    }
}
