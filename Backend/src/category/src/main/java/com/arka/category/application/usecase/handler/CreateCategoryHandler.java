package com.arka.category.application.usecase.handler;



import org.springframework.stereotype.Service;

import com.arka.category.application.port.in.ICreateCategoryUseCase;
import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import com.arka.category.domain.model.Category;


import lombok.RequiredArgsConstructor;;


@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements ICreateCategoryUseCase {

    
    private final ICategoryRepository categoryRepository; 

    private final IUserDataPort userRepository;

    @Override
    public Category execute (CreateCategoryCommand cmd) throws BusinessRuleException{
        //Validar que el solicitante tenga permisos de acción para este evento
        UserInfo requester = userRepository.findById(cmd.getRequesterId());

        if (requester.getRole().name() == Roleinfo.Cliente.name()){throw new BusinessRuleException("Permisos Insuficientes para esta acción");}

        //Validar existencia antes de intentar crear
        if (categoryRepository.existsByName(cmd.getName())){throw new BusinessRuleException("Categoria ya exitente");}       
        
        //Dumpeamos de command a modelo
        Category c = cmd.toModel();     

        //Persistir
        c = categoryRepository.save(c);

        //Retornar
        return c;

    }


}
