package com.arka.product.domain.valueObject;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class ProductStockTest {

    @Test
    void create_stock_succesfully(){
        ProductStock pStock = new ProductStock(20);
        assertEquals(Integer.valueOf(20),pStock.getValue());
    }

    @Test
    void should_thrown_when_invalid_properties(){
        assertAll(
            ()->{
                assertThrows(InvalidPropertiesGiven.class, (()->{new ProductStock(null);}));
                assertThrows(InvalidPropertiesGiven.class, (()->{new ProductStock(-1);}));
            }
        );
    }
    
}
