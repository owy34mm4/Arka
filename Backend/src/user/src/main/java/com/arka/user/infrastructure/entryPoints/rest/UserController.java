package com.arka.user.infrastructure.entryPoints.rest;

import org.springframework.http.ResponseEntity;


import com.arka.user.application.port.in.ICreateUserUseCase;
import com.arka.user.application.port.in.IUpdateUserUsecase;
import com.arka.user.application.usecase.command.CreateUserCommand;
import com.arka.user.application.usecase.command.UpdateUserCommand;

import com.arka.user.domain.model.User;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.RequestCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.ResponseCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser.RequestUpdateUser;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("api/v0/user")
@RequiredArgsConstructor
public class UserController {
    
    private final ICreateUserUseCase createUserUseCase;

    private final IUpdateUserUsecase updateUserUseCase;
    
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

    @PutMapping("/update")
    public ResponseEntity<ResponseCreateUser> putMethodName(@RequestBody RequestUpdateUser request) {
        //generamos el command via request
        UpdateUserCommand cmd = UpdateUserCommand.createFromRequest(request);

        //Ejecutamos el UseCase
        User userResponse = updateUserUseCase.execute(cmd);

        var response = ResponseCreateUser.createFromModel(userResponse, "Modificacion de Usuario Exitosa");
        
        return ResponseEntity.ok(response);
    }
    
    
}
