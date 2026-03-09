package com.arka.user.domain.valueObject;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;
import com.arka.user.domain.valueObjects.UserName;

public class UserNameTest {

    @Test
    void should_create_valid_name(){
        UserName name = UserName.create("Luis", "Javier", "Jaramillo", "Cruz");
        assertAll(
            ()->{
                assertEquals("Luis", name.getFirstName());
                assertEquals("Javier", name.getLastName());
                assertEquals("Jaramillo", name.getFirstSurname());
                assertEquals("Cruz", name.getLastSurname());
            }
        );
    }

    @Test
    void should_thrown_when_firstName_or_firstSurname_blank(){
        assertAll(
            ()->{
                assertThrows(InvalidPropertiesGiven.class, (()->{UserName.create("", "Javier", "Jaramillo", "Cruz");}));
                assertThrows(InvalidPropertiesGiven.class, (()->{UserName.create("Jaramillo", "Javier", "", "Cruz");}));
                assertThrows(InvalidPropertiesGiven.class, (()->{UserName.create("", "Javier", "", "Cruz");}));
            }
        );
    }
    
    
}
