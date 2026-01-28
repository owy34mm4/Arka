package com.arka.category.api.rest.mapper;  
  
import org.springframework.stereotype.Component;

import com.arka.category.api.rest.dto.RequestCreateCategory;
import com.arka.category.api.rest.mapper.CategoryApiMapper;
import com.arka.category.application.usecase.command.CreateCategoryCommand;
/**  
 * MAPPER - Convierte DTOs de API a Commands de Aplicación.  
 */  
@Component  
public class CategoryApiMapper {  
      
    public CreateCategoryCommand toCommand(RequestCreateCategory request) {  
        return CreateCategoryCommand.builder()
            .id(null)
            .name(request.getName())
            .productsId(null)    
            .build();
    }  
      
     
}