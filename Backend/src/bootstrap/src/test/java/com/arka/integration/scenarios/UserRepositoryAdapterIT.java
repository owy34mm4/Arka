package com.arka.integration.scenarios;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.arka.integration.BasePersistanceIT;
import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;


public class UserRepositoryAdapterIT extends BasePersistanceIT {
    
    @Test
    @Transactional
    @DisplayName("should save and retrieve user from real MySQL")
    void should_save_and_search_user(){
        User uClient =User.createUser(
            "Jaime", "Cecil",
            "Pinto", "Meril",
            "test@gmail.com", "usuarioValido",
            "contraseñaValida",
            Role.Cliente);
        
        userRepository.save(uClient);

        User found = userRepository.findById(uClient.getId());

        assertNotNull(found);
        assertAll(
            ()->{
                assertEquals(uClient.getName().getFirstName(), found.getName().getFirstName());
                assertEquals(uClient.getName().getLastName(), found.getName().getLastName());
                assertEquals(uClient.getName().getFirstSurname(), found.getName().getFirstSurname());
                assertEquals(uClient.getName().getLastSurname(), found.getName().getLastSurname());
                assertEquals(uClient.getEmail(), found.getEmail());
                assertEquals(uClient.getUsername(), found.getUsername());
                assertEquals(uClient.getPassword(), found.getPassword());
                assertEquals(Role.Cliente.name(), found.getRole().name());
            }
        );
    }

    @Test
    @Transactional
    @DisplayName("should save, update and retrieve user from real MySQL")
    void should_update_and_search_user(){
        User uClient =User.createUser(
            "Jaime", 
            "Cecil",
            "Pinto", 
            "Meril",
            "test@gmail.com", 
            "usuarioValido",
            "contraseñaValida",
            Role.Cliente
        );
        
        userRepository.save(uClient);

        User found = userRepository.findById(uClient.getId());

        User uNewData = User.createUser("newFName",
        "newLName",
        "new FSNAME",
        "new LSNAME", 
        "newEmail@test.com",
        "newUsername",
        "newPWORD",
        Role.Cliente);

        found.editUserInstance(uNewData);

        userRepository.save(found);

        User updatedUser = userRepository.findById(uClient.getId());

        
        assertAll(
            ()->{
                assertNotNull(found);

                assertEquals(uNewData.getName().getFirstName(), updatedUser.getName().getFirstName());
                assertEquals(uNewData.getName().getLastName(), updatedUser.getName().getLastName());
                assertEquals(uNewData.getName().getFirstSurname(), updatedUser.getName().getFirstSurname());
                assertEquals(uNewData.getName().getLastSurname(), updatedUser.getName().getLastSurname());
                assertEquals(uNewData.getEmail(), updatedUser.getEmail());
                assertEquals(uNewData.getUsername(), updatedUser.getUsername());
                assertEquals(uNewData.getPassword(), updatedUser.getPassword());
                assertEquals(Role.Cliente.name(), updatedUser.getRole().name());
                }
            );
    }

}
