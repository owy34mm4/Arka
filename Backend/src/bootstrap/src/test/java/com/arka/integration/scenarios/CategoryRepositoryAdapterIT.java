package com.arka.integration.scenarios;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.arka.category.domain.model.Category;
import com.arka.integration.BasePersistanceIT;

public class CategoryRepositoryAdapterIT extends BasePersistanceIT{

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
