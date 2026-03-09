package com.arka.category.application.usecase.command;






import com.arka.category.domain.model.Category;
import com.arka.category.domain.valueObject.CategoryName;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.RequestCreateCategory;

 

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder @Getter @Setter
public class CreateCategoryCommand {
    Long id;
    String name;

    
    public static CreateCategoryCommand createFromRequest(RequestCreateCategory request){
        return CreateCategoryCommand.builder()
        .id(null)
        .name(request.getName())
        .build();
    }

    public Category toModel(){
        return Category.builder()
        .id(this.id)
        .name(CategoryName.create(this.name))
        .productsId(null)
        .products(null)
        .build();
    }
}
