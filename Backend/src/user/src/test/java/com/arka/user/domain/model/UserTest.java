package com.arka.user.domain.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;
import com.arka.user.domain.model.enums.Role;

public class UserTest {

    private User buildDefaultUserWithRole(Role rol){
        return User.createUser(
            "Jaime", "Cecil",
            "Pinto", "Meril",
            "test@gmail.com", "usuarioValido",
            "contraseñaValida",
            rol);
    }

    private User buildAdminUserWithName(String firstName, String lastName, String firstSurname, String lastSurname){
        return User.createUser(
            firstName, lastName,
            firstSurname, lastSurname,
            "test@gmail.com", "usuarioValido",
            "contraseñaValida",
            Role.Administrador);
    }

    @Test
    void should_create_user_with_valid_info(){
        User uAdmin = buildDefaultUserWithRole(Role.Administrador);
        User uClient = buildDefaultUserWithRole(Role.Cliente);
        User uEmployee = buildDefaultUserWithRole(Role.Empleado);

        assertAll(
            ()->{
                assertEquals(uAdmin.getName().getFirstName(), "Jaime");
                assertEquals(uAdmin.getName().getLastName(), "Cecil");
                assertEquals(uAdmin.getName().getFirstSurname(), "Pinto");
                assertEquals(uAdmin.getName().getLastSurname(), "Meril");
                assertEquals(uAdmin.getEmail(), "test@gmail.com");
                assertEquals(uAdmin.getUsername(), "usuarioValido");
                assertEquals(uAdmin.getPassword(), "contraseñaValida");

                // Checks that method can created all the roles
                assertEquals(uAdmin.getRole().name(), Role.Administrador.name());
                assertEquals(uClient.getRole().name(), Role.Cliente.name());
                assertEquals(uEmployee.getRole().name(), Role.Empleado.name());
                
            }
        );
    }

    @Test
    void should_trown_when_name_is_invalid(){

        assertThrows(
            InvalidPropertiesGiven.class,
            (()->{buildAdminUserWithName("", "usuario sin nombre 1", "", "ni apellido 1");})
        );
        


    }


    @Test
    /**
     * Should update only the 'soft data' in user.
     * Not supposed to update the timestamps or booleans
     * 
     * This test doesnt update the Role because the requester isnt admin
     */
    void should_update_user_info_with_no_role_update(){
        User uClient = buildDefaultUserWithRole(Role.Cliente);

        User uNewData = buildAdminUserWithName("jose", "enrique", "salamanca","juarez");
        uNewData.setActive(false);

        uClient.editUserInstance(uNewData);

        assertAll(
            ()->{
                // Check the data thats supposed to get edit
                assertEquals(uClient.getId(), uNewData.getId());

                assertEquals(uClient.getName().getFirstName(), uNewData.getName().getFirstName());
                assertEquals(uClient.getName().getLastName(), uNewData.getName().getLastName());
                assertEquals(uClient.getName().getFirstSurname(), uNewData.getName().getFirstSurname());
                assertEquals(uClient.getName().getLastSurname(), uNewData.getName().getLastSurname());

                assertEquals(uClient.getEmail(), uNewData.getEmail());
                assertEquals(uClient.getUsername(), uNewData.getUsername());
                assertEquals(uClient.getPassword(), uNewData.getPassword());

                // Checks the data thats not supposed to get edit
                assertNotEquals(uClient.getCreatedAt(), uNewData.getCreatedAt());
                assertNotEquals(uClient.isActive(), uNewData.isActive());
                assertNotEquals(uClient.getRole().name(), uNewData.getRole().name());
            }
        );


    }

    
}
