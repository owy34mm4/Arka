package com.arka.category.application.usecase.handler;



import org.springframework.stereotype.Service;

import com.arka.category.application.port.in.ICreateCategoryUseCase;
import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.application.usecase.command.CreateCategoryCommand;

import com.arka.category.domain.model.Category;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;;


@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements ICreateCategoryUseCase {

    
    private final ICategoryRepository categoryRepository; 

    private final IUserExternalRepository userRepository;

    @Override
    public Category execute (CreateCategoryCommand cmd) throws InvalidPropertiesGiven{
        //Validar que el solicitante sea un id Real
        if (!userRepository.existsById(cmd.getRequesterId())){return Category.builder().build();}

        //Validar existencia antes de intentar crear
        if (categoryRepository.existsByName(cmd.getName())){return Category.builder().build();}       
        
        //Dumpeamos de command a modelo
        Category c = cmd.toModel();     

        //Persistir
        c = categoryRepository.save(c);

        //Retornar
        return c;

    }


}
