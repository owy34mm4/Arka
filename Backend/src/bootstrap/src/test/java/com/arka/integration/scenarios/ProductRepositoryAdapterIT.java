package com.arka.integration.scenarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arka.integration.BasePersistanceIT;
import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.persistence.repository.auxiliarObjects.LowStockProductDTO;
import com.arka.shared.domain.exceptions.NotFoundException;

public class ProductRepositoryAdapterIT extends BasePersistanceIT {
    // Factory 
    private Product createDefaultProductWithId(Long id){
        return Product.create(
                id,
                "ProductoNuevo",
                "DescripcionProductoNuevo",
                2000,
                10,
                List.of(1L),
                null,
                null,
                null
            );
    }

    private Product createLowStockProductWithId(Long id){
        return Product.create(
                id,
                "ProductoNuevo",
                "DescripcionProductoNuevo",
                2000,
                2,
                List.of(1L),
                null,
                null,
                null
            );
    }
    // ─── SAVE ────────────────────────────────────────────────  
    @Test  
    @DisplayName("Should save a product and return it with generated ID")  
    void should_save_product() {  
        Product product =createDefaultProductWithId(1L);
            
        Product saved = productRepository.save(product);  
  
        assertNotNull(saved.getId());  
        assertEquals("Laptop Dell", saved.getName());  
        assertEquals(10, saved.getStock());  
    }  
  
    // ─── FIND BY ID ──────────────────────────────────────────  
    @Test  
    @DisplayName("Should find product by ID")  
    void should_find_product_by_id() {  
        Product saved = productRepository.save(
            createDefaultProductWithId(1L)
        );
        
        Product found = productRepository.findById(saved.getId());  
  
        assertNotNull(found);  
        assertEquals(saved.getId(), found.getId());  
        assertEquals("Monitor LG", found.getName());  
    }  
  
    @Test  
    @DisplayName("Should throw NotFoundException when product does not exist")  
    void should_throw_when_product_not_found() {  
        assertThrows(NotFoundException.class,  
            () -> productRepository.findById(999L)  
        );  
    }  
  
    // ─── FIND ALL BY ID ──────────────────────────────────────  
    @Test  
    @DisplayName("Should return list of products by IDs")  
    void should_find_all_by_ids() {  
        Product p1 = productRepository.save(createDefaultProductWithId(1L));  
        Product p2 = productRepository.save(createDefaultProductWithId(2L));  
  
        List<Product> result = productRepository.findAllById(List.of(p1.getId(), p2.getId()));  
  
        assertEquals(2, result.size());  
    }  
  
    // ─── EXISTS BY ID ────────────────────────────────────────  
    @Test  
    @DisplayName("Should return true when product exists")  
    void should_return_true_when_exists() {  
        Product saved = productRepository.save(createDefaultProductWithId(1L));  
  
        assertTrue(productRepository.existsById(saved.getId()));  
    }  
  
    @Test  
    @DisplayName("Should return false when product does not exist")  
    void should_return_false_when_not_exists() {  
        assertFalse(productRepository.existsById(999L));  
    }  
  
    // ─── LOW STOCK ───────────────────────────────────────────  
    @Test  
    @DisplayName("Should return products below stock threshold")  
    void should_find_products_below_stock() {  
        productRepository.save(createLowStockProductWithId(1L));  
        productRepository.save(createLowStockProductWithId(2L));  
  
        List<LowStockProductDTO> result = productRepository.findProductsBelowStock(5);  
  
        assertEquals(1, result.size());  
        assertEquals("Stock Bajo", result.get(0).getName());  
    }  
  
    @Test  
    @DisplayName("Should throw NotFoundException when no products below threshold")  
    void should_throw_when_no_low_stock_products() {  
        // BD vacía o todos con stock alto  
        assertThrows(NotFoundException.class,  
            () -> productRepository.findProductsBelowStock(5)  
        );  
    }
}
