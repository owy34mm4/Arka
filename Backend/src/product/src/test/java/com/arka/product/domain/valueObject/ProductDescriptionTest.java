package com.arka.product.domain.valueObject;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class ProductDescriptionTest {
    
    @Test
    void should_create_description_successfully(){
        ProductDescription pDescription = new ProductDescription("Descripcion Completamente Valida");
        assertEquals("Descripcion Completamente Valida", pDescription.getValue());
    }

    @Test
    void should_thrown_when_invalid_properties(){
        assertAll(
            ()->{
                assertThrows(InvalidPropertiesGiven.class, (()->new ProductDescription(null)));
                assertThrows(InvalidPropertiesGiven.class, (()->new ProductDescription("")));
                assertThrows(InvalidPropertiesGiven.class, (()->new ProductDescription("Muy Corta")));
            }
        );
    }

    
}
