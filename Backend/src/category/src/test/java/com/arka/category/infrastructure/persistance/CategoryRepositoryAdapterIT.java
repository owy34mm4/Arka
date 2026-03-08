package com.arka.category.infrastructure.persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.arka.category.application.CategoryTestApplication;
import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.domain.model.Category;
import com.arka.shared.domain.IntegrationTest;

@SpringBootTest(
    classes = CategoryTestApplication.class           
)
@Testcontainers
@IntegrationTest
public class CategoryRepositoryAdapterIT {           

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
    private ICategoryRepository categoryRepository;

    @Test
    @Transactional
    @DisplayName("Should save and retrieve category from real MySQL")
    void should_save_and_find_category() {
        Category category = Category.createCategory("Nombre Valido");

        category = categoryRepository.save(category);
        Category found = categoryRepository.findById(category.getId());

        assertNotNull(found);
        assertEquals("Nombre Valido", found.getName().getValue());
    }

    @Test
    @Transactional
    @DisplayName("Should return null when category does not exist")
    void should_return_null_when_not_exists() {
        Category found = categoryRepository.findById(999L);
        assertNull(found);
    }
}