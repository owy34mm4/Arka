package com.arka.category.infrastructure.entryPoints.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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

import com.arka.category.application.CategoryTestApplication;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.RequestCreateCategory;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.ResponseCreateCategory;
import com.arka.shared.domain.IntegrationTest;


@SpringBootTest(
    classes = CategoryTestApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@IntegrationTest
public class CategoryControllerIT {

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
    @DisplayName("POST /api/categories - Should create category and return 201 Created")  
    void should_create_category_successfully() {  
        // 1. ARRANGE (Preparar)  
        RequestCreateCategory request = RequestCreateCategory.builder()  
                .name("Electronics")  
                .build();  
  
        // Si tienes seguridad activa, aquí añadirías el Header  
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_JSON);  
        // headers.setBearerAuth("tu_token_aqui");   
  
        HttpEntity<RequestCreateCategory> entity = new HttpEntity<>(request, headers);  
  
        // 2. ACT (Ejecutar) - Llamada real por HTTP  
        ResponseEntity<ResponseCreateCategory> response = restTemplate.postForEntity(  
                "/api/v0/category/create",   
                entity,   
                ResponseCreateCategory.class  
        );  
  
        // 3. ASSERT (Verificar)  
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getName(), response.getBody().getName()); 
          
        // Opcional: Verificar que el ID no sea nulo (significa que se guardó en MySQL)  
        assertNotNull(response.getBody().getId());
        // assertThat(response.getBody().getId()).isNotNull();  
    }
    
}
