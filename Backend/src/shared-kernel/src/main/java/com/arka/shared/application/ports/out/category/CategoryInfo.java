package com.arka.shared.application.ports.out.category;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CategoryInfo{
    private Long id;
    private String name;
    
    public static CategoryInfo create(Long id, String name){
        return CategoryInfo.builder()
                        .id(id)
                        .name(name)
                .build();
    }

    public String toString(){
        return this.getName();
    }

}