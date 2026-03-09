package com.arka.user.appliaction.usecase.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.CreateUserCommand;
import com.arka.user.application.usecase.handler.CreateUserHandler;
import com.arka.user.domain.model.User;

@ExtendWith(MockitoExtension.class)
public class CreateUserHandlerTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IEmailNotificationPort emailPort;
    @Mock
    private IPasswordEncoderPort passwordEncoder;

    @InjectMocks
    private CreateUserHandler createUserHandler;

    // Data Reutilizable de tests

    private CreateUserCommand validCommand;

    @Captor
    ArgumentCaptor<Map<String, Object>> mapCaptor;


    @BeforeEach
	void setUp(){
	    validCommand = CreateUserCommand.builder()
            .id(null)
            .first_name("Julio")
            .last_name("Enrique")
            .first_surname("Zapata")
            .last_surname("Quiroz")
            .email("test@gmail.com")
            .username("usernameValido")
            .password("PasswordValida")
            .role(null)
        .build();     
	}

// ----Test 1 --- HAPPY PATH -------
    @Test
    @DisplayName("Should create user when username && email are not assigned to another user")
    void should_create_user_sucesfully(){
        when(userRepository.alreadyExists(validCommand.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(validCommand.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("PasswordValida")).thenReturn("endodedPassword");

        when(userRepository.save(any(User.class))).thenAnswer(invocation-> invocation.getArgument(0));

        User result = createUserHandler.execute(validCommand);

        assertNotNull(result);
        assertEquals("test@gmail.com", result.getEmail());
        assertEquals("usernameValido", result.getUsername());
        assertEquals("endodedPassword", result.getPassword());

        verify(userRepository, times(1)).save(any(User.class));

        verify(emailPort, times(1)).sendHtml(
            eq(result.getEmail()),
            eq("Usuario Creado Exitosamente"),
            eq("welcome.html"),
            mapCaptor.capture()
        );

        Map<String, Object> variables = mapCaptor.getValue();  
        assertAll(
            ()->{
                assertEquals("Arka", variables.get("company_name"));  
                assertEquals(result.getName().getFulName(), variables.get("customer_name"));
                assertEquals(result.getEmail(), variables.get("customer_email"));
            }
        );
    }

// --- Test 2 ----- Username Ya existe
    @Test  
    @DisplayName("Should throw when username already exists")  
    void should_throw_when_username_exists() {  
    
        when(userRepository.alreadyExists(validCommand.getUsername())).thenReturn(true);  
    
        assertThrows(BusinessRuleException.class,  
            () -> createUserHandler.execute(validCommand));  
    
        verify(userRepository, never()).save(any());  
        verify(emailPort, never()).sendHtml(any(), any(), any(), any());  
    }

//--- Test 3 ---- Email ya existe
    @Test
    @DisplayName("Should thrown when email already exists")
    void should_thrwn_when_email_exists(){
        when(userRepository.alreadyExists(validCommand.getUsername())).thenReturn(false);

        when(userRepository.existsByEmail(validCommand.getEmail())).thenReturn(true);

        assertThrows(BusinessRuleException.class, (()->createUserHandler.execute(validCommand)));

        verify(userRepository, never()).save(any());

        verify(emailPort, never()).sendHtml(any(), any(), any(), any());

    }


}
