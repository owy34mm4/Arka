package com.arka.product.domain.valueObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import com.arka.product.domain.valueObjects.ProductName;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class ProductNameTest {
    
    @Test
    void should_create_valid_Name(){
        ProductName pName = new ProductName("NombreCompletamenteValido");
        assertEquals("NombreCompletamenteValido", pName.getValue());
    }

    @Test
    void should_thrown_when_invalid_properties(){
        assertAll(
            ()->{
                assertThrows(InvalidPropertiesGiven.class, (()-> new ProductName("")));
                assertThrows(InvalidPropertiesGiven.class,(()-> new ProductName(null)));
            }
        );
    }
            
       
}
