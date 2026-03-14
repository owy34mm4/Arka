package com.arka.e2e.scenarios;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.ResponseCreateCategory;
import com.arka.e2e.BaseE2E;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.RequestCreateProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.ResponseCreateProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.ResponseUpdateStockProduct;

public class ProductE2E extends BaseE2E{
    
    @Test
    @DisplayName("Escenario : Admin crea un nuevo producto FullFlow")
    void admin_creates_new_product(){
        //Logea como usuario admin
        String token = loginInAdmin();

        //Creates Category in E2E
        ResponseCreateCategory responseCategory = assertDoesNotThrow(()->createCategory(token, "Electronica")).getBody();
        assertNotNull(responseCategory);
        var createdCategroyId = responseCategory.getId();

        //Creates the product in E2E
        RequestCreateProduct requestCreateProduct = RequestCreateProduct.builder()
            .name("MouseGamingLogitech")
            .description("Wireless Gaming Mouse")
            .price(22000)
            .stock(10)
            .categories(List.of(createdCategroyId))
        .build();
        
        assertAll(
            ()->{
                ResponseCreateProduct responseCreateProduct = assertDoesNotThrow(()-> createProduct(token, requestCreateProduct).getBody());
                assertNotNull(responseCreateProduct);

                assertEquals(requestCreateProduct.getName(), responseCreateProduct.getName());
                assertEquals(requestCreateProduct.getDescription(), responseCreateProduct.getDescription());
                assertEquals(requestCreateProduct.getPrice(), responseCreateProduct.getPrice());
                assertEquals(requestCreateProduct.getStock(), responseCreateProduct.getStock());
                assertEquals(requestCreateProduct.getCategories(), responseCreateProduct.getCategories());
            }
        );
        
    }

    @Test
    @DisplayName("Escenario: Admin actualiza el stock de un producto FullFlow")
    void admin_updated_old_product_stock(){
        //Logea como usuario admin
        String token = loginInAdmin();

        //Creates Category in E2E
        ResponseCreateCategory responseCategory = assertDoesNotThrow(()->createCategory(token, "Electronica")).getBody();
        assertNotNull(responseCategory);
        var createdCategroyId = responseCategory.getId();

        //Creates the product in E2E
        RequestCreateProduct requestCreateProduct = RequestCreateProduct.builder()
            .name("MouseGamingLogitech")
            .description("Wireless Gaming Mouse")
            .price(22000)
            .stock(10)
            .categories(List.of(createdCategroyId))
        .build();

        ResponseCreateProduct responseCreateProduct = assertDoesNotThrow(()-> createProduct(token, requestCreateProduct).getBody());
        assertNotNull(responseCreateProduct);
        var createdProductId = responseCreateProduct.getId();

        assertAll(
            ()->{
                ResponseUpdateStockProduct responseUpdateProduct = assertDoesNotThrow(()-> updateProductStock(token, createdProductId, 48).getBody());
                assertNotNull(responseUpdateProduct);
                assertNotEquals(responseCreateProduct.getStock(), responseUpdateProduct.getStock());
            }
        );
        
    }
}
