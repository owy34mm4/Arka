package com.arka.user.application.usecase.handler;

import org.springframework.stereotype.Service;

import com.arka.user.application.port.in.ICreateUserUseCase;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.CreateUserCommand;
import com.arka.user.domain.model.User;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateUserHandler implements ICreateUserUseCase {

    private final IUserRepository userRepository;

    @Override
    public User execute(CreateUserCommand cmd) {
        //Checkear si ya existe alguien con el username del request
        if (userRepository.alreadyExists(cmd.getUsername())){
            //Si ya existe entonces cancelamos flujo
            return User.builder().build();
        }

        //Creamos el ModelDomain para trabajar
        User u = cmd.toModel();

        //Guardamos y sobreescribimos referencia
        u = userRepository.save(u);

        return u;

        
    }
    
}
