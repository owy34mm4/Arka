package com.arka.product.domain.exception;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InvalidPropertiesGiven extends Exception {
    public InvalidPropertiesGiven (String msg){super(msg);}
    
}
