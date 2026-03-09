package com.arka.user.application.usecase.handler;

import java.util.Map;


import org.springframework.stereotype.Service;


import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;
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

    private final IPasswordEncoderPort passwordEncoder;

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

        //Usamos metodo de dominio para ejecutar
        User u = User.createUser(
            cmd.getFirst_name(), cmd.getLast_name(), cmd.getFirst_surname(), cmd.getLast_surname(),
            cmd.getEmail(),cmd.getUsername(), passwordEncoder.encode(cmd.getPassword()) , Role.Cliente
            );

        //Guardamos y sobreescribimos referencia
        u = userRepository.save(u);
        
        //Notificar al cliente
            Map<String,Object> variablesHtml = Map.of(
                "company_name","Arka",
                "customer_name",u.getName().getFirstName()+" "+u.getName().getFirstSurname(),
                "customer_email",u.getEmail()
            );
            emailPort.sendHtml(u.getEmail(), "Usuario Creado Exitosamente", "welcome.html", variablesHtml );
        return u;

        
    }
    
}
