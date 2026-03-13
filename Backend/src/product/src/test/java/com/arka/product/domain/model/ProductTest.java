package com.arka.product.domain.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class ProductTest {
    
    //Factory
    private Product createDefaultProduct(){
        return Product.create(
            1L, 
            "Halls", 
            "Barra de 10 Unidades",
            2000, 
            50, 
            List.of(2L), 
            null, 
            null, 
            null
        );
    }

    @Test
    void should_create_product_sucesfully(){
        assertAll(
            ()->{
                Product p = assertDoesNotThrow(()->createDefaultProduct());

                assertEquals(1L, p.getId());

                assertEquals("Halls", p.getName().getValue());

                assertEquals("Barra de 10 Unidades", p.getDescription().getValue());

                assertEquals(2000, p.getPrice().getValue());
                assertEquals("cop", p.getPrice().getCurrency());

                assertEquals(50, p.getStock().getValue());

                assertEquals(List.of(2L), p.getCategoriesIds().getValues());
            }
        );
       
    }

    @Test
    void should_thrown_when_invalid_properties_to_creation(){
        assertThrows(InvalidPropertiesGiven.class, (
                ()->{
                    Product.create(null, null, null, null, null, null, null, null, null);
                }
            )
        );
    }

    @Test
    void should_thrown_when_invalid_properties_to_update_stock(){
        Product p = createDefaultProduct();
        assertAll(
            ()->{
                assertThrows(InvalidPropertiesGiven.class, (()->p.updateStock(null)));
                assertThrows(InvalidPropertiesGiven.class, (()->p.updateStock(-1)));
            }
        );
    }


}
