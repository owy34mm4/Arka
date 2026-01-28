package com.arka.shared.domain.exceptions;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InvalidPropertiesGiven extends Exception {
    public InvalidPropertiesGiven (String msg){super(msg);}
    
}
