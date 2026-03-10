package com.arka.user.infrastructure.entryPoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.arka.shared.domain.IntegrationTest;
import com.arka.user.appliaction.UserTestApplication;

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

    



}
