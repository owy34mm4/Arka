package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductPrice {
    
    Integer value;
    String currency;

    public ProductPrice (Integer value, String currency){
        if (value<0 || value ==null || !currency.equalsIgnoreCase("COP")) { throw new InvalidPropertiesGiven("Product");}

        this.value=value;
        this.currency=currency;

    }
}
