package com.arka.product.application.useCase.handler;


import java.util.List;


import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;


import com.arka.category.infrastructure.persistence.repository.external.gateway.ICategoryExternalRepository;
import com.arka.product.application.port.in.ICreateProductUseCase;
import com.arka.product.application.port.out.IProductHistoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;






@Service
@RequiredArgsConstructor
public class CreateProductHandler implements ICreateProductUseCase {
    
    private final ICategoryExternalRepository categoryRepository;
    
    private final IProductRepositoryPort productRepository ;

    private final IProductHistoryPort productHistoryRepository;

    private final IUserExternalRepository userRepository;

    @Override
    public Product execute(CreateProductCommand cmd) throws BusinessRuleException {
        //Validar privilegios de accion -> Min empleado
        if(!userRepository.isClient(cmd.getRequesterId()) || !userRepository.isAdmin(cmd.getRequesterId())){throw new BusinessRuleException("Autorizacion insuficiente para la accion");}

        //Obtener las categorias de su repositorio
        List <CategoryInfo> categories = categoryRepository.findAllById(cmd.getCategories());
        
        //Generamos modelo 
        Product model = cmd.toModel();
        
        //Checkear el modelo
        model.checkIstance();

        //guardamos Producto
        model = productRepository.save(model);

        //Inyectamos Categorias(Validacion en Dominio)
        model.inyectCategories(categories);

        //Guardar historyRecord
        productHistoryRepository.save(model.toProductHistory());
        
        return model;
        
    }
    
}
