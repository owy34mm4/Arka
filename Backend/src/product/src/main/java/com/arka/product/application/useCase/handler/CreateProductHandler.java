package com.arka.product.application.useCase.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.category.ICategoryDataPort;
import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import lombok.RequiredArgsConstructor;

import com.arka.product.application.port.in.ICreateProductUseCase;
import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.product.domain.model.ProductHistory;


@Service
@RequiredArgsConstructor
public class CreateProductHandler implements ICreateProductUseCase {
    
    private final ICategoryDataPort categoryRepository;
    
    private final IProductRepositoryPort productRepository ;

    private final IProductHistoryRepositoryPort productHistoryRepository;

    private final IUserDataPort userRepository;
    
    private final IAuthenticateUserPort authenticateUserPort;


    private boolean isAdminOrEmployee(UserInfo requester){
        return requester.getRole().name().equals(Roleinfo.Empleado.name()) || requester.getRole().name().equals( Roleinfo.Administrador.name()) ;
    }

    @Override
    public Product execute(CreateProductCommand cmd) throws BusinessRuleException {
        //Validar privilegios de accion -> Min empleado
            Long requesterId = authenticateUserPort.getUserId();
            UserInfo requester =  userRepository.findById(requesterId);

            if(!isAdminOrEmployee(requester)){throw new BusinessRuleException("Autorizacion insuficiente para la accion");}

        //Generamos modelo 
        Product model = Product.create(
            cmd.getId(),
            cmd.getName(), 
            cmd.getDescription(), 
            cmd.getPrice(), 
            cmd.getStock(), 
            cmd.getCategories(), 
            null, 
            null, 
            null
        );

        //Buscar Categorias - Criterio Aceptacion
            //Obtener las categorias de su repositorio -- Error 404 si no existe <- EarlyError
            List <CategoryInfo> categories = categoryRepository.findAllById(cmd.getCategories());
       
        //Persistir
      
            //Guardamos Producto
            model = productRepository.save(model);

            //History Record
                ProductHistory pH = model.toProductHistory();
                
                //Sets De MetaData

                    //TimeStamp de Creacion y Marca de Responsabilidad <-Metodo Dominio
                    pH.establishForCreation(requester.getId());
                    
                //Guardar -- No retorna nada. Solo persiste para Log
                    productHistoryRepository.save(pH);

        //Inyectar para respuesta 
            
            //Inyectamos Categorias(Validacion en Dominio)
            model.inyectCategories(categories);
        
        return model;
        
    }
    
}
