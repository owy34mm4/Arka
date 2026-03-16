package com.arka.product.domain.valueObject;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class ProductPriceTest {
    
    @Test
    void should_create_price_sucesfully(){
        ProductPrice pPrice = new ProductPrice(1000, "COP");
        assertEquals(Integer.valueOf(1000), pPrice.getValue());
    }

    @Test
    void should_thrown_when_invalid_properties(){
        assertAll(
            ()->{
                //Currency must be 'cop'
                assertThrows(InvalidPropertiesGiven.class, (()-> new ProductPrice(1000, "COOP")));
                //Price check
                assertThrows(InvalidPropertiesGiven.class, (()-> new ProductPrice(-1, "COP")));
                assertThrows(InvalidPropertiesGiven.class, (()-> new ProductPrice(null, "COP")));
            }
        );
    }
}
