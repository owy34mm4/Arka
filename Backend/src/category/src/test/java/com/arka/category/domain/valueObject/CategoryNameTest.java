package com.arka.category.domain.valueObject;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

public class CategoryNameTest {

    @Test
    void should_create_valid_name(){
        CategoryName cName = CategoryName.create("NombreValidisimo");
        assertEquals("NombreValidisimo", cName.getValue());
    }

    @Test
    void should_throw_when_invalid_properties(){
        assertThrows(InvalidPropertiesGiven.class , (()-> CategoryName.create(null)));
        assertThrows(InvalidPropertiesGiven.class , (()-> CategoryName.create("")));

    }
    
}
