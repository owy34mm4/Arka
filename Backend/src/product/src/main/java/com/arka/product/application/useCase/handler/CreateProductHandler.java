package com.arka.product.application.useCase.handler;


import java.time.Instant;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;


import com.arka.category.infrastructure.persistence.repository.external.gateway.ICategoryExternalRepository;
import com.arka.product.application.port.in.ICreateProductUseCase;
import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.product.domain.model.ProductHistory;






@Service
@RequiredArgsConstructor
public class CreateProductHandler implements ICreateProductUseCase {
    
    private final ICategoryExternalRepository categoryRepository;
    
    private final IProductRepositoryPort productRepository ;

    private final IProductHistoryRepositoryPort productHistoryRepository;

    private final IUserExternalRepository userRepository;

    @Override
    public Product execute(CreateProductCommand cmd) throws BusinessRuleException {
        //Validar privilegios de accion -> Min empleado
            //Leer ley de Morgan en operaciones booleanas
            if(!userRepository.isEmploye(cmd.getRequesterId()) && !userRepository.isAdmin(cmd.getRequesterId())){throw new BusinessRuleException("Autorizacion insuficiente para la accion");}

        //Generamos modelo 
        Product model = cmd.toModel();

        //Buscar Categorias - Criterio Aceptacion
            //Obtener las categorias de su repositorio -- Error 404 si no existe
            List <CategoryInfo> categories = categoryRepository.findAllById(cmd.getCategories());
        
        //Persistir
      
            //Guardamos Producto
            model = productRepository.save(model);

            //History Record
                ProductHistory pH = model.toProductHistory();
                
                //Sets De MetaData

                    //TimeStamp de Creacion
                    pH.setCreatedAt(Date.from(Instant.now()));
                    //Marca de Responsabilidad
                    pH.setCreatedById(cmd.getRequesterId());
                
                //Guardar -- No retorna nada. Solo persiste para Log
                    productHistoryRepository.save(pH);

        //Inyectar para respuesta 
            
            //Inyectamos Categorias(Validacion en Dominio)
            model.inyectCategories(categories);
        
        return model;
        
    }
    
}
