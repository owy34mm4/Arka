package com.arka.category.application.usecase.command;






import com.arka.category.domain.model.Category;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.RequestCreateCategory;

 

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
    Long id;
    String name;
    Long requesterId;

    
    public static CreateCategoryCommand createFromRequest(RequestCreateCategory request){
        return CreateCategoryCommand.builder()
        .id(null)
        .name(request.getName())
        .requesterId(request.getRequester_id())
        .build();
    }

    public Category toModel(){
        return Category.builder()
        .id(this.id)
        .name(this.name)
        .productsId(null)
        .products(null)
        .build();
    }
}
