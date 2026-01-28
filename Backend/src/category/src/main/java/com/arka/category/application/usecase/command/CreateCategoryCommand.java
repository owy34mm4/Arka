package com.arka.category.application.usecase.command;

import java.util.List;




import jakarta.validation.constraints.NotBlank;  

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**  
 * COMMAND - Representa la intención de crear un producto.  
 *   
 * Es un DTO inmutable que contiene todos los datos necesarios  
 * para ejecutar el caso de uso.  
 */

@Builder @Getter @Setter
public class CreateCategoryCommand {
    @NotBlank 
    Long id;
    @NotBlank 
    String name;
    List<Long> productsId;


    
}
