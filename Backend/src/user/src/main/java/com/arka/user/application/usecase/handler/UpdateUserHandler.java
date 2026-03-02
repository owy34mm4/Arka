package com.arka.user.application.usecase.handler;


import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;
import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.application.port.in.IUpdateUserUsecase;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.UpdateUserCommand;
import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserHandler implements IUpdateUserUsecase{

    private final IUserRepository userRepository;

    private final IPasswordEncoderPort passwordEncoder;

    private final IAuthenticateUserPort authenticateUserPort;
    

    @Override
    public User execute(UpdateUserCommand cmd) {
        
        if (authenticateUserPort.getUserId() != cmd.getIdToModify() ){
            throw new BusinessRuleException("Accion no permitida");
        }
        User oldUser = userRepository.findById(cmd.getIdToModify());
        User u = cmd.toModel();

        //Accion de Admin -> Cambiar al role especificado
        if(authenticateUserPort.getRole() == Role.Administrador.name()){
            u.setRole(cmd.getRole());
        }
        //Eliminar despues de crear un administrador en sistema

        u.setRole(cmd.getRole());
        u.setCreatedAt(oldUser.getCreatedAt());
        u.setLastLogin(oldUser.getLastLogin());
        u.setActive(oldUser.isActive());
        
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u = userRepository.save(u);

        return u;

    }
    
}
