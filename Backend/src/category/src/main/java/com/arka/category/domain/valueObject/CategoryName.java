package com.arka.category.domain.valueObject;


import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryName {

    private String value;
    
    public static CategoryName create (String value){
        if(value.strip().isBlank() || value ==null) throw new InvalidPropertiesGiven("CategoryName");
        return CategoryName.builder()
        .value(value)
        .build();
    }

    
}
