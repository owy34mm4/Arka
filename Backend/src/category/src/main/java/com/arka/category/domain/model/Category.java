package com.arka.category.domain.model;

import java.util.List;



import com.arka.category.domain.exception.InvalidIDException;
import com.arka.category.domain.exception.InvalidNameException;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Builder
public class Category {
    private Long id;
    private String name;
    private List<Long> productsId;

    public static Category create(Long id, String name, List<Long> products) throws InvalidNameException, InvalidIDException{
        validateName(name);
        //validateId(id);

        return Category.builder()
                        .id(id)
                        .name(name)
                        .productsId(products)
                .build();

    }

    private static void validateName(String nameToCheck) throws InvalidNameException{
        if(nameToCheck.strip().isBlank() || nameToCheck ==null) throw new InvalidNameException();
    }

    private static void validateId(Long idToCheck) throws InvalidIDException{
        if( idToCheck == null || idToCheck<=0) throw new InvalidIDException("Id invalido");
    }
}
    