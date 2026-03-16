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
        var requesterId = authenticateUserPort.getUserId();
        var requesterRole = authenticateUserPort.getRole();

        boolean isOwner = requesterId.equals(cmd.getIdToModify());  
        boolean isAdmin = Role.Administrador.name().equals(requesterRole);  
        
        if (!isOwner && !isAdmin) {  
        throw new BusinessRuleException("Accion no permitida");  
        }
        
        User u = cmd.toModel();
        
        u.setPassword(passwordEncoder.encode(u.getPassword()));

        User oldUser = userRepository.findById(cmd.getIdToModify());

        oldUser.editUserInstance(u);

        //Accion de Admin -> Cambiar al role especificado
        if(isAdmin){
            oldUser.setRole(u.getRole());
        }

        oldUser = userRepository.save(oldUser);

        return oldUser;

    }
    
}
