package com.arka.user.infrastructure.entryPoints.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.user.application.port.in.ILoginUseCase;
import com.arka.user.application.usecase.command.LogInCommand;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.RequestLogin;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.ResponseLogIn;

import lombok.RequiredArgsConstructor;

@RestController  
@RequestMapping("/api/auth")  
@RequiredArgsConstructor  
public class AuthController {  
  
    private final ILoginUseCase loginUseCase;  
     
  
    @PostMapping("/login")  
    public ResponseEntity<ResponseLogIn> login(@RequestBody RequestLogin request) {  
        LogInCommand command = LogInCommand.createFromRequest(request);
  
        return ResponseEntity.ok(loginUseCase.execute(command));  
    }  
  
}  