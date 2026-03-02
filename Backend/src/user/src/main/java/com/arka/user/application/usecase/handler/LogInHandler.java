package com.arka.user.application.usecase.handler;

import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.application.port.in.ILoginUseCase;
import com.arka.user.application.port.out.ITokenGenerator;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.LogInCommand;
import com.arka.user.domain.model.User;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.ResponseLogIn;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogInHandler implements ILoginUseCase {

    private final IUserRepository userRepository;  
    private final ITokenGenerator tokenGenerator;
    private final IPasswordEncoderPort passwordEncoder;

    @Override
    public ResponseLogIn execute(LogInCommand cmd) {
        // 1. Buscar usuario  
        User user = userRepository.findByUsername(cmd.getUsername()); 
  
        // 2. Validar contraseña (asume que guardas hash con BCrypt)  
        if (!passwordMatches(cmd.getPassword(), user.getPassword())) {  
            throw new BusinessRuleException("Credenciales inválidas");  
        }  
  
        // 3. Generar token  
        String token = tokenGenerator.generateToken(user.getUsername(), user.getRole().name(), user.getId());  
  
        return ResponseLogIn.builder()  
            .token(token)  
            .username(user.getUsername())  
            .role(user.getRole().name())  
            .userId(user.getId())
            .build();  
    }  
  
    private boolean passwordMatches(String raw, String encoded) {  
        return  passwordEncoder.matches(raw, encoded);  
    }

}
    

