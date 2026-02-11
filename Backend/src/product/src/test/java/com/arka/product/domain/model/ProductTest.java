package com.arka.product.domain.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.arka.product.domain.valueObjects.ProductCategoriesIds;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;

class ProductTest {

    @Nested
    @DisplayName("ProductCreation")
    public class ProductCreation {

        @Test
        @DisplayName("Debe crear el producto con la informacion valida")
        void shouldCreateProductWithValidData(){
            Product product= Product.builder()
            .name(new ProductName("Laptop Gaming"))
            .description(new ProductDescription("Its a 16GB RAM Gaming Laptop, suitable and pretty wild"))
            .price(new ProductPrice(2000000, "COP"))
            .stock(new ProductStock(20))
            .categoriesIds(new ProductCategoriesIds(List.of(1L, 2L)))
            .build();

            //Comprueba que los valores se hayan creado correctamente
            assertAll(
                ()->assertEquals("Laptop Gaming", product.getName().getValue()),
                ()->assertEquals("Its a 16GB RAM Gaming Laptop, suitable and pretty wild", product.getDescription().getValue()),

                //AssertsPrice ( Doble valor en ValueObject)
                ()->assertAll(
                    ()->assertEquals(2000000, product.getPrice().getValue()),
                    ()->assertEquals("COP", product.getPrice().getCurrency())
                    ),
                
                ()->assertEquals(20, product.getStock()),
                ()->assertEquals(2, product.getCategoriesIds().getValues().size())
            );


        }
        
        

    }
    
}
