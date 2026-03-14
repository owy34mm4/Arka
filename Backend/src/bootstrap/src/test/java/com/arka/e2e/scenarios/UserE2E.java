package com.arka.e2e.scenarios;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.arka.e2e.BaseE2E;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.ResponseCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser.RequestUpdateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser.ResponseUpdateUser;

public class UserE2E extends BaseE2E {
    
    @Test
    @DisplayName("Escecario: Requester sin credenciales crea un usuario")
    void unauthorized_creates_user(){
        //Actua
        ResponseCreateUser response  = createUser("username", "password").getBody();

        assertNotNull(response);
    }

    @Test
    @DisplayName("Escenario: Admin actualiza la info de un usuario")
    void admin_updates_user_info(){
        //Creates the user to modify
        ResponseCreateUser responseCreate = createUser("usuario_update", "password_update").getBody();
        //LogIn as setUp Admin
        String token = loginInAdmin();

        RequestUpdateUser request = RequestUpdateUser.builder()
            .id_to_modify(responseCreate.getId())
            .first_name("newFName")
            .last_name("newLName")
            .username("nuevo_usuario")
            .password("nueva_password")
            .email("new_email@test.com")

        .build();
        ResponseUpdateUser responseUpdate = updateUser(token, request).getBody();

        assertNotNull(responseUpdate);

    }
    
}
