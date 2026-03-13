package com.arka.integration.scenarios;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arka.integration.BasePersistanceIT;
import com.arka.product.domain.model.ProductHistory;

public class ProductHistoryRepositoryAdapterIT extends BasePersistanceIT{

    @Test  
    @DisplayName("Should save product history record")  
    void should_save_product_history() {  
        ProductHistory history = ProductHistory.builder()  
            .productId(1L)  
            .build();  
        history.establishForCreation(1L);
  
        ProductHistory saved = productHistoryRepository.save(history);  
        
        assertAll(
            ()->{
                assertNotNull(saved.getId());  

                assertEquals(1L, saved.getProductId());  
                assertInstanceOf(Date.class, saved.getCreatedAt());
                assertEquals(history.getCreatedById(), saved.getCreatedById());
            }
        );
        
    }

    
}
