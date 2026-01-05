package com.arka.category.api.rest.dto.mapper;  
  
import org.springframework.stereotype.Component;

import com.arka.category.api.rest.dto.CreateCategoryRequest;
import com.arka.category.api.rest.dto.mapper.CategoryApiMapper;
import com.arka.category.application.usecase.command.CreateCategoryCommand;
/**  
 * MAPPER - Convierte DTOs de API a Commands de Aplicación.  
 */  
@Component  
public class CategoryApiMapper {  
      
    public CreateCategoryCommand toCommand(CreateCategoryRequest request) {  
        return CreateCategoryCommand.builder()
            .id(null)
            .name(request.getName())
            .products(null)    
            .build();
    }  
      
     
}