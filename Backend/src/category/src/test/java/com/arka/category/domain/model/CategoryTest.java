package com.arka.category.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import com.arka.category.domain.valueObject.CategoryName;

public class CategoryTest {

    //FactoryMethod para tests
    private Category buildCategoryWithName( String name ) {  
        return Category.builder()  
           .id(1L)
           .name(CategoryName.create(name))
           .products(null)
           .productsId(null)
            .build();  
    }

    @Test
    void should_create_valid_category(){
        assertAll(
            ()->{
                Category c = assertDoesNotThrow(()->buildCategoryWithName("nombreValido"));

                assertEquals("nombreValido", c.getName().getValue());
            }
        );
        
    }
    





}
