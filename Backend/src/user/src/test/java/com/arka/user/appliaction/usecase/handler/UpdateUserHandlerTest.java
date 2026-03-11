package com.arka.user.appliaction.usecase.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;
import com.arka.shared.application.ports.out.security.IPasswordEncoderPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.application.usecase.command.UpdateUserCommand;
import com.arka.user.application.usecase.handler.UpdateUserHandler;
import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;

@ExtendWith(MockitoExtension.class)
public class UpdateUserHandlerTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IPasswordEncoderPort passwordEncoder;

    @Mock
    private IAuthenticateUserPort authenticateUserPort;
    
    @InjectMocks
    private UpdateUserHandler updateUserHanlder;

    private UpdateUserCommand validCommand;

    private User oldUserClient;

    @BeforeEach
	void setUp(){
	    validCommand = UpdateUserCommand.builder()
            .idToModify(1L)
            .first_name("Julio")
            .last_name("Enrique")
            .first_surname("Zapata")
            .last_surname("Quiroz")
            .email("test@gmail.com")
            .username("usernameValido")
            .password("PasswordValida")
            .role(Role.Administrador)
        .build();     

        oldUserClient = User.createUser(
            "fisrtName",
            "lastName",
            "firstSurname",
            "lastSurname",
            "email",
            "username",
            "password",
            Role.Cliente);
        oldUserClient.setId(1L);
	}

// --- Test 1 ---- HappyPath -- requester is admin and userToModify
    @Test
    @DisplayName("Updates the info using the Domain Method, and also updates the role, bc requester is admin" )
    void should_update_user_sucesfully(){

        when(authenticateUserPort.getUserId()).thenReturn(1L);
        when(authenticateUserPort.getRole()).thenReturn(Role.Administrador.name());

        when(userRepository.findById(1L)).thenReturn(oldUserClient);

        when(passwordEncoder.encode(validCommand.getPassword())).thenReturn("validSHA512");

        when(userRepository.save(any(User.class))).thenAnswer(invocation-> invocation.getArgument(0));

        User result = updateUserHanlder.execute(validCommand);

        assertNotNull(result);

        verify(userRepository, times(1)).save(any(User.class));

        assertAll(
            ()->{
                // Checks the name update
                assertEquals(result.getName().getFirstName(), validCommand.getFirst_name());
                assertEquals(result.getName().getLastName(), validCommand.getLast_name());
                assertEquals(result.getName().getFirstSurname(), validCommand.getFirst_surname());
                assertEquals(result.getName().getLastSurname(), validCommand.getLast_surname());

                // Checks the credentials update
                assertEquals(result.getUsername(), validCommand.getUsername());
                assertEquals(result.getPassword(), "validSHA512");

                // Checks the Role update
                assertEquals(result.getRole().name(), validCommand.getRole().name());

                // Check the data that wasnt mean to update
                assertEquals(result.getCreatedAt(), oldUserClient.getCreatedAt());
                

            }
        );

    }

// --- Test 2 ---- Requester is userToModify, not admin
    @Test
    @DisplayName("Updates the info using the Domain Method; because requester is the userToModify, doesnt updates the role, bc requester is not admin" )
    void should_update_user_sucesfully_with_no_role(){

        when(authenticateUserPort.getUserId()).thenReturn(1L);
        when(authenticateUserPort.getRole()).thenReturn(Role.Empleado.name());

        when(userRepository.findById(1L)).thenReturn(oldUserClient);

        when(passwordEncoder.encode(validCommand.getPassword())).thenReturn("validSHA512");

        when(userRepository.save(any(User.class))).thenAnswer(invocation-> invocation.getArgument(0));

        User result = updateUserHanlder.execute(validCommand);

        assertNotNull(result);

        verify(userRepository, times(1)).save(any(User.class));

        assertAll(
            ()->{
                // Checks the name update
                assertEquals(result.getName().getFirstName(), validCommand.getFirst_name());
                assertEquals(result.getName().getLastName(), validCommand.getLast_name());
                assertEquals(result.getName().getFirstSurname(), validCommand.getFirst_surname());
                assertEquals(result.getName().getLastSurname(), validCommand.getLast_surname());

                // Checks the credentials update
                assertEquals(result.getUsername(), validCommand.getUsername());
                assertEquals(result.getPassword(), "validSHA512");

                // Check the data that wasnt mean to update
                // Checks the Role update
                assertNotEquals(result.getRole().name(), validCommand.getRole().name());
                assertEquals(result.getCreatedAt(), oldUserClient.getCreatedAt());
                

            }
        );

    }

// --- Test 3 ---- Requester is Admin but no userToModify
    @Test
    @DisplayName("Updates the info using the Domain method,and also updates the role; beacuse requester is admin")
    void should_update_user_sucesfully_with_role(){

        when(authenticateUserPort.getUserId()).thenReturn(99L);
        when(authenticateUserPort.getRole()).thenReturn(Role.Administrador.name());

        when(userRepository.findById(1L)).thenReturn(oldUserClient);

        when(passwordEncoder.encode(validCommand.getPassword())).thenReturn("validSHA512");

        when(userRepository.save(any(User.class))).thenAnswer(invocation-> invocation.getArgument(0));

        User result = updateUserHanlder.execute(validCommand);

        assertNotNull(result);

        verify(userRepository, times(1)).save(any(User.class));

        assertAll(
            ()->{
                // Checks the name update
                assertEquals(result.getName().getFirstName(), validCommand.getFirst_name());
                assertEquals(result.getName().getLastName(), validCommand.getLast_name());
                assertEquals(result.getName().getFirstSurname(), validCommand.getFirst_surname());
                assertEquals(result.getName().getLastSurname(), validCommand.getLast_surname());

                // Checks the credentials update
                assertEquals(result.getUsername(), validCommand.getUsername());
                assertEquals(result.getPassword(), "validSHA512");

                // Checks the Role update
                assertEquals(result.getRole().name(), validCommand.getRole().name());

                // Check the data that wasnt mean to update
                assertEquals(result.getCreatedAt(), oldUserClient.getCreatedAt());
                

            }
        );

    }

// --- Test 4 ---- Requester is not owner and neither admin
    @Test
    @DisplayName("Should thrown when requester is neither userToModify or admin" )
    void should_thrown_when_requester_is_not_the_modified_one_and_is_not_admin(){

        when(authenticateUserPort.getUserId()).thenReturn(99L);
        when(authenticateUserPort.getRole()).thenReturn(Role.Empleado.name());

        assertThrows(BusinessRuleException.class,  
            () -> updateUserHanlder.execute(validCommand)
        );

        verify(userRepository, never()).save(any(User.class));
        
    }


}
