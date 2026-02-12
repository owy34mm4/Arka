package com.arka.order.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class OrderTotal {
    String currency;
    Integer value;

    public OrderTotal(String currency, Integer value){
        if(!currency.equalsIgnoreCase("COP") && !currency.equalsIgnoreCase("USD")) {throw new InvalidPropertiesGiven("Order");}
        if(value<0){throw new InvalidPropertiesGiven("Order");}

        this.currency=currency;
        this.value=value;

    }
}
