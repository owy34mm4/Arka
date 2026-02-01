package com.arka.product.application.useCase.handler;


import java.util.List;


import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.RequiredArgsConstructor;

import com.arka.category.infrastructure.persistence.repository.external.adapter.CategoryDataAdapter;
import com.arka.product.application.port.in.ICreateProductUseCase;
import com.arka.product.application.port.out.IProductHistoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;






@Service
@RequiredArgsConstructor
public class CreateProductHandler implements ICreateProductUseCase {
    
    private final CategoryDataAdapter categoryRepository;
    
    private final IProductRepositoryPort productRepository ;

    private final IProductHistoryPort productHistoryRepository;

    
    /**
     * Aquí se Ejecuta el Caso de Uso, Recibe parametro <CreateProductCommand>
     * @param 
     * cmd CreateProductCommand -> Es el comando de inicio, se mapea en el controller
     * @return 
     * Model<Product> -> El objeto resultante del proceso de transformacion y valdiacion
     * 
     * @throws InvalidPropertiesGiven(CustomException)
     */
    @Override
    public Product execute(CreateProductCommand cmd) throws InvalidPropertiesGiven {
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
