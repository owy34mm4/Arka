package com.arka.category.api.rest.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter 
public class ResponseCreateCategory{   
    Long id;
    String name;
    String message;

    public static  ResponseCreateCategory create(Long id, String name, String message){
        return ResponseCreateCategory.builder()
                .id(id)
                .name(name)
                .message(message)
            .build();
    }
}
