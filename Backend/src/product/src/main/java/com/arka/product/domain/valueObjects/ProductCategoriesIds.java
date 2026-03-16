package com.arka.product.domain.valueObjects;

import java.util.List;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductCategoriesIds {
    List<Long> values;

    public ProductCategoriesIds(List<Long> values){
        if ( values ==null || values.size()<=0 || values.isEmpty() ) {throw new InvalidPropertiesGiven("Product"); }

        this.values = values;

    }
    
}
