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
      @DisplayName("should sab and retrieve user from real MySQL")
      void should_save_and_search_user(){
        User uClient =User.createUser(
            "Jaime", "Cecil",
            "Pinto", "Meril",
            "test@gmail.com", "usuarioValido",
            "contraseñaValida",
            Role.Cliente);
        
        uClient = userRepository.save(uClient);

        User found = userRepository.findById(uClient.getId());

        assertNotNull(found);
       assertAll(
            ()->{
                assertEquals("Jaime", found.getName().getFirstName());
                assertEquals("Cecil", found.getName().getLastName());
                assertEquals("Pinto", found.getName().getFirstSurname());
                assertEquals("Meril", found.getName().getLastSurname());
                assertEquals("test@gmail.com", found.getEmail());
                assertEquals("usuarioValido", found.getUsername());
                assertEquals("contraseñaValida", found.getPassword());
                assertEquals(Role.Cliente.name(), found.getRole().name());
            }
        );
      }

    
}
