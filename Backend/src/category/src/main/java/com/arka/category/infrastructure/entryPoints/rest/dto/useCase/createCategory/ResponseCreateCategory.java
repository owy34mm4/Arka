package com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory;

import com.arka.category.domain.model.Category;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter 
public class ResponseCreateCategory{   
    Long id;
    String name;
    String message;

    public static  ResponseCreateCategory createFromModel(Category model, String msg){
        return ResponseCreateCategory.builder()
                .id(model.getId())
                .name(model.getName())
                .message(msg)
            .build();
    }
}
