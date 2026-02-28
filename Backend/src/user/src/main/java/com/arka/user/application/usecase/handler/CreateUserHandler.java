package com.arka.user.application.usecase.handler;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.application.port.in.ICreateUserUseCase;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.CreateUserCommand;
import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateUserHandler implements ICreateUserUseCase {

    private final IUserRepository userRepository;

    private final IEmailNotificationPort emailPort;

    @Override
    public User execute(CreateUserCommand cmd) {
        //Checkear si ya existe alguien con el username del request
        if (userRepository.alreadyExists(cmd.getUsername())){
           throw new BusinessRuleException("Usuario ya existe");
        }
        //Validamos que el email no esté asignado a otro usuario 
        if (userRepository.existsByEmail(cmd.getEmail())){
            throw new BusinessRuleException("Correo Asignado a otro Usuario");
        }

        //Creamos el ModelDomain para trabajar
        User u = cmd.toModel();
        
        //Sets propios del useCase
        u.setRole(Role.Cliente);
        u.setCreatedAt(LocalDateTime.now());
        u.setActive(true);

        //Guardamos y sobreescribimos referencia
        u = userRepository.save(u);
        
        //Notificar al cliente
            Map<String,Object> variablesHtml = Map.of(
                "company_name","Arka",
                "customer_name",u.getFirstName()+" "+u.getFirstSurname(),
                "customer_email",u.getEmail()
            );
            emailPort.sendHtml(u.getEmail(), "Usuario Creado Exitosamente", "welcome.html", variablesHtml );
        return u;

        
    }
    
}
