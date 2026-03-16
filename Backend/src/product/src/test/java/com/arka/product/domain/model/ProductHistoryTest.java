package com.arka.product.domain.model;



import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class ProductHistoryTest {
    
    //Factory nullsy
    ProductHistory createNullsyProductHistory(){
        return ProductHistory.builder().build();
    }

    @Test
    void should_set_flags_of_creation_log(){
        assertAll(
            ()->{
                ProductHistory pH =assertDoesNotThrow(()->createNullsyProductHistory());

                assertDoesNotThrow(()->pH.establishForCreation(1L));

                assertNotNull(pH.getCreatedAt());
                assertInstanceOf(Date.class, pH.getCreatedAt());
                assertEquals(1L, pH.getCreatedById());
            }
        );
    }

    @Test
    void should_set_flags_of_modification_log(){
        assertAll(
            ()->{
                ProductHistory pH =assertDoesNotThrow(()->createNullsyProductHistory());

                assertDoesNotThrow(()->pH.establishForModification(1L));

                assertNotNull(pH.getModifiedAt());
                assertInstanceOf(Date.class, pH.getModifiedAt());
                assertEquals(1L, pH.getModifiedById());
            }
        );
    }


}
