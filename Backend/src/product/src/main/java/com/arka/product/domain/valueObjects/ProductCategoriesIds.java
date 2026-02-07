package com.arka.product.domain.valueObjects;

import java.util.List;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Getter;

@Getter
public class ProductCategoriesIds {
    List<Long> values;

    public ProductCategoriesIds(List<Long> values){
        if (values.size()<=0 || values.isEmpty() || values ==null) {throw new InvalidPropertiesGiven("Product"); }

        this.values = values;

    }
    
}
