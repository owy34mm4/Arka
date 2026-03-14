package com.arka.user.appliaction.usecase.handler;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.shared.domain.exceptions.NotFoundException;
import com.arka.user.application.port.out.ITokenGenerator;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.LogInCommand;
import com.arka.user.application.usecase.handler.LogInHandler;
import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.ResponseLogIn;

@ExtendWith(MockitoExtension.class)
public class LoginHandlerTest {
    
    @Mock  
    IUserRepository userRepository;  
  
    @Mock  
    IPasswordEncoderPort passwordEncoder;  
  
    @Mock  
    ITokenGenerator tokenGenerator;  
  
    @InjectMocks  
    LogInHandler handler;  
  
    private User user;  

    private LogInCommand command;  
  
    @BeforeEach  
    void setup() {  
        user = User.builder()  
                .id(1L)  
                .username("admin")  
                .password("hashedPass")  
                .role(Role.Administrador)  
                .build();  
  
        command = new LogInCommand("admin", "rawPass");  
    }  


    @Test  
    @DisplayName("Should login successfully and return token")  
    void should_login_successfully() {  
    
        when(userRepository.findByUsername("admin")).thenReturn(user);  
        when(passwordEncoder.matches("rawPass", "hashedPass")).thenReturn(true);  
        when(tokenGenerator.generateToken("admin", "Administrador", 1L))  
                .thenReturn("jwt-token");  
    
        ResponseLogIn response = handler.execute(command);  
    
        assertNotNull(response);  
        assertEquals("admin", response.getUsername());  
        assertEquals("Administrador", response.getRole());  
        assertEquals(1L, response.getUserId());  
        assertEquals("jwt-token", response.getToken());  
    
        verify(tokenGenerator).generateToken(anyString(), anyString(), anyLong());  
    }

    @Test
    @DisplayName("Should throw when user does not exist")
    void should_throw_when_user_not_found() {

        when(userRepository.findByUsername("admin")).thenReturn(null);

        assertThrows(BusinessRuleException.class,
            () -> handler.execute(command)
        );
    }

    @Test
    @DisplayName("Should throw when password is invalid")
    void should_throw_when_password_invalid() {

        when(userRepository.findByUsername("admin")).thenReturn(user);
        when(passwordEncoder.matches("rawPass", "hashedPass")).thenReturn(false);

        assertThrows(BusinessRuleException.class,
            () -> handler.execute(command)
        );
    }

    @Test
    @DisplayName("Should fail if token generator fails")
    void should_fail_if_token_generator_fails() {

        when(userRepository.findByUsername("admin")).thenReturn(user);
        when(passwordEncoder.matches("rawPass", "hashedPass")).thenReturn(true);
        when(tokenGenerator.generateToken(any(), any(), any())).thenReturn(null);

        ResponseLogIn response = handler.execute(command);

        assertNull(response.getToken());
    }

    
}


