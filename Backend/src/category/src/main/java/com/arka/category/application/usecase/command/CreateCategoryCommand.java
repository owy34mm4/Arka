package com.arka.category.application.usecase.command;

import java.util.List;

import com.arka.category.application.port.in.ICreateCategoryUseCase;
import com.arka.category.domain.model.Category;
import com.arka.product.domain.model.Product;

import jakarta.validation.constraints.NotBlank;  

import lombok.Builder;


/**  
 * COMMAND - Representa la intención de crear un producto.  
 *   
 * Es un DTO inmutable que contiene todos los datos necesarios  
 * para ejecutar el caso de uso.  
 */

@Builder
public record CreateCategoryCommand( 
    @NotBlank Long id,
    @NotBlank String name ,
    List<Product> products
) implements ICreateCategoryUseCase{
    
    @Override
    public Category execute(CreateCategoryCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    
}
