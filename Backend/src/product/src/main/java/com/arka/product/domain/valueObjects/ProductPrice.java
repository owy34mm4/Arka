package com.arka.product.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductPrice {
    
    private final Integer value;
    String currency;

    public ProductPrice (Integer value, String currency){
        if ( value ==null || value<0 || !currency.equalsIgnoreCase("COP")) { throw new InvalidPropertiesGiven("Product");}

        this.value=value;
        this.currency=currency;

    }
}
