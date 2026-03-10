package com.arka.user.domain.model;

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

    
}
