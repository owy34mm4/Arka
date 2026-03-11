package com.arka.user.infrastructure.persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.arka.shared.domain.IntegrationTest;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.domain.model.User;
import com.arka.user.domain.model.enums.Role;
import com.arka.user.infrastructure.persistence.repository.internal.adapter.UserRepositoryAdapter;

import jakarta.transaction.Transactional;

@SpringBootTest(classes = UserRepositoryAdapter.class )
@Testcontainers
@IntegrationTest
public class UserRepositoryAdapterIT {

    @Container
	  static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.4")
	      .withDatabaseName("arka_test")
	      .withUsername("test")
	      .withPassword("test");
	
	  @DynamicPropertySource
	  static void configureProperties(DynamicPropertyRegistry registry) {
	    registry.add("spring.datasource.url", mysql::getJdbcUrl);
	    registry.add("spring.datasource.username", mysql::getUsername);
	    registry.add("spring.datasource.password", mysql::getPassword);
	    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
	  }

      @Autowired
      private IUserRepository userRepository;

      @Test
      @Transactional
      @DisplayName("should save and retrieve user from real MySQL")
      void should_save_and_search_user(){
        User uClient =User.createUser(
            "Jaime", "Cecil",
            "Pinto", "Meril",
            "test@gmail.com", "usuarioValido",
            "contraseñaValida",
            Role.Cliente);
        
        userRepository.save(uClient);

        User found = userRepository.findById(uClient.getId());

        assertNotNull(found);
       assertAll(
            ()->{
                assertEquals(uClient.getName().getFirstName(), found.getName().getFirstName());
                assertEquals(uClient.getName().getLastName(), found.getName().getLastName());
                assertEquals(uClient.getName().getFirstSurname(), found.getName().getFirstSurname());
                assertEquals(uClient.getName().getLastSurname(), found.getName().getLastSurname());
                assertEquals(uClient.getEmail(), found.getEmail());
                assertEquals(uClient.getUsername(), found.getUsername());
                assertEquals(uClient.getPassword(), found.getPassword());
                assertEquals(Role.Cliente.name(), found.getRole().name());
            }
        );
    }

    @Test
    @Transactional
    @DisplayName("should save, update and retrieve user from real MySQL")
    void should_update_and_search_user(){
        User uClient =User.createUser(
            "Jaime", "Cecil",
            "Pinto", "Meril",
            "test@gmail.com", "usuarioValido",
            "contraseñaValida",
            Role.Cliente);
        
        userRepository.save(uClient);

        User found = userRepository.findById(uClient.getId());

        User uNewData = User.createUser("newFName",
        "newLName",
        "new FSNAME",
        "new LSNAME", 
        "newEmail@test.com",
        "newUsername",
        "newPWORD",
        Role.Cliente);

        found.editUserInstance(uNewData);

        userRepository.save(found);

        User updatedUser = userRepository.findById(uClient.getId());

        assertNotNull(found);
    assertAll(
            ()->{
                assertEquals(uNewData.getName().getFirstName(), updatedUser.getName().getFirstName());
                assertEquals(uNewData.getName().getLastName(), updatedUser.getName().getLastName());
                assertEquals(uNewData.getName().getFirstSurname(), updatedUser.getName().getFirstSurname());
                assertEquals(uNewData.getName().getLastSurname(), updatedUser.getName().getLastSurname());
                assertEquals(uNewData.getEmail(), updatedUser.getEmail());
                assertEquals(uNewData.getUsername(), updatedUser.getUsername());
                assertEquals(uNewData.getPassword(), updatedUser.getPassword());
                assertEquals(Role.Cliente.name(), updatedUser.getRole().name());
                }
            );
    }

    
}
