package com.arka.product.domain.valueObject;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.arka.product.domain.valueObjects.ProductCategoriesIds;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class ProductCategoriesIdsTest {
    
    @Test
    void should_create_categories_ids_sucessfully(){
        ProductCategoriesIds pCategoriesIds = new ProductCategoriesIds(List.of(1L));
        assertEquals(List.of(1L), pCategoriesIds.getValues());
    }

    @Test
    void should_thrown_when_invalid_properties(){
        assertAll(
            ()->{
                assertThrows(InvalidPropertiesGiven.class, (()->{new ProductCategoriesIds(null);}));
                assertThrows(InvalidPropertiesGiven.class, (()->{new ProductCategoriesIds(List.of());}));
            }
        );
    }
}
