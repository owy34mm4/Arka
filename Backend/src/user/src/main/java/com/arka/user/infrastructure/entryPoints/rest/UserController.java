package com.arka.user.infrastructure.entryPoints.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arka.user.application.usecase.command.CreateUserCommand;
import com.arka.user.application.usecase.handler.CreateUserHandler;
import com.arka.user.domain.model.User;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.RequestCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.ResponseCreateUser;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v0/user")
@RequiredArgsConstructor
public class UserController {
    
    private final CreateUserHandler createUserUseCase;
    
    @PostMapping("/create")
    public ResponseEntity<ResponseCreateUser> createUser(@RequestBody RequestCreateUser request) {
        //Generamos el command via request 
        CreateUserCommand cmd = CreateUserCommand.createFromRequest(request);

        //Ejecutamos le useCase
        User modelResponse = createUserUseCase.execute(cmd);

        //Generamos la response
        ResponseCreateUser response = ResponseCreateUser.createFromModel(modelResponse, "Creacion de Usuario Exitosa");

        return ResponseEntity.ok(response);
    }
    
    
}
