package com.arka.product.application.useCase.handler;


import java.util.List;


import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.RequiredArgsConstructor;

import com.arka.category.infrastructure.persistence.repository.adapter.external.CategoryDataAdapter;
import com.arka.product.application.mapper.adapter.CreateProductLayerMapper;
import com.arka.product.application.port.in.ICreateProductUseCase;
import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.domain.model.Product;

import com.arka.product.infrastructure.persistence.repository.adapter.ProductRepositoryAdapter;






@Service
@RequiredArgsConstructor
public class CreateProductHandler implements ICreateProductUseCase {
    
    private final CategoryDataAdapter categoryRepository;
    
    private final ProductRepositoryAdapter repository ;

    private final CreateProductLayerMapper layerMapper;


 
    
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
        //Mapear comando a Dominio
        Product model = layerMapper.toDomain(cmd);

        //Buscar categorias, con su propio repositorio
        List<CategoryInfo> categories = categoryRepository.findAllById(model.getCategoriesIds());

        //Inyectar categorias al modelo
        model.inyectCategoriesFromRespository(categories);

        //Checkear el modelo
        model.checkIstance();

        //guardar
        Product savedModel = repository.save(model);

        return savedModel;
        
    }
    
}
