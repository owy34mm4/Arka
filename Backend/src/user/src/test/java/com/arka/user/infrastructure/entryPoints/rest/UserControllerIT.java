package com.arka.user.infrastructure.entryPoints.rest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.arka.shared.domain.IntegrationTest;
import com.arka.user.appliaction.UserTestApplication;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.RequestCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.ResponseCreateUser;

@SpringBootTest(
    classes = UserTestApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@IntegrationTest
public class UserControllerIT {

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
	    // Forzamos a Hibernate a crear las tablas en el contenedor 
	    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop"); 
	
	  }

	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	@DisplayName("POST /api/v0/user/create - Should create user and return 200 oktgb ")
    void should_create_user_sucesfully(){
		// 1. ARRANGE (Preparar) 
	
	    RequestCreateUser request = RequestCreateUser.builder() 
	        .first_name("Elkin")
			.last_name("De Jesus")
			.first_surname("Barragán")
			.last_surname("Escudero")
			.email("y0rmun009@gmail.com")
			.username("usernameValid")
			.password("validPWord")
	        .build(); 
	
	    // Si tienes seguridad activa, aquí añadirías el Header 
	
	    HttpHeaders headers = new HttpHeaders(); 

	    headers.setContentType(MediaType.APPLICATION_JSON); 
	
	    HttpEntity<RequestCreateUser> entity = new HttpEntity<>(request, headers); 
	
	    // 2. ACT (Ejecutar) - Llamada real por HTTP 
	
	    ResponseEntity<ResponseCreateUser> response = restTemplate.postForEntity( 
	        "/api/v0/user/create", 	
	        entity, 	
	        ResponseCreateUser.class 	
	    ); 
	
	    // 3. ASSERT (Verificar) 
		
		assertAll(
			()->{
				assertNotNull(response.getBody());

				assertNotNull(response.getBody().getId());
				assertEquals(HttpStatus.OK, response.getStatusCode());
				
				assertEquals(request.getFirst_name(), response.getBody().getFirstName());
				assertEquals(request.getLast_name(), response.getBody().getLastName());
				assertEquals(request.getFirst_surname(), response.getBody().getFirstSurname());
				assertEquals(request.getLast_surname(), response.getBody().getLastSurname());
				assertEquals(request.getEmail(), response.getBody().getEmail());
				assertEquals(request.getUsername(), response.getBody().getUsername());
				assertEquals(request.getPassword(), response.getBody().getPassword());
	
			}
		);
	    
	

	}



}
