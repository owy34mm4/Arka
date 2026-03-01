package com.arka.user.application.usecase.handler;

import org.springframework.stereotype.Service;

import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.application.port.in.IUpdateUserUsecase;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.UpdateUserCommand;
import com.arka.user.domain.model.User;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserHandler implements IUpdateUserUsecase{

    private final IUserRepository userRepository;
    

    @Override
    public User execute(UpdateUserCommand cmd) {
        //Validar que el dueño del usuario o un Admin sean los que va a actuar
        if (cmd.getRequesterId() != cmd.getIdToModify() ){
            throw new BusinessRuleException("Accion no permitida");
        }
        User oldUser = userRepository.findById(cmd.getIdToModify());
        User u = cmd.toModel();

        //Accion de Admin -> Cambiar al role especificado
        if(userRepository.isAdmin(cmd.getRequesterId())){
            u.setRole(cmd.getRole());
        }
        //Eliminar despues de crear un administrador en sistema
        u.setRole(cmd.getRole());
        u.setCreatedAt(oldUser.getCreatedAt());
        u.setLastLogin(oldUser.getLastLogin());
        u.setActive(oldUser.isActive());
        
        u = userRepository.save(u);

        return u;

    }
    
}
