package com.arka.product.application.useCase.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arka.category.infrastructure.persistence.repository.adapter.external.CategoryDataAdapter;

import com.arka.product.application.port.in.IUpdateStocktUseCase;
import com.arka.product.application.useCase.command.UpdateStockCommand;
import com.arka.product.domain.model.Product;


import com.arka.product.infrastructure.persistence.repository.adapter.ProductRepositoryAdapter;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UpdateStockProductHandler implements IUpdateStocktUseCase{

    private final CategoryDataAdapter categoryRepository;

    private final ProductRepositoryAdapter repository ;

    
    @Override
    public Product execute(UpdateStockCommand cmd) throws InvalidPropertiesGiven {
        //Obtenemos la entidad que vamos a modificar
        Product modelToModify = repository.findById(cmd.getProductId());

        //Agregamos con metodo de Dominio ( Validacion-In-method)
        modelToModify.updateStock(cmd.getNewStock());

        //Preparar Respuesta

        //Buscar categorias, con su propio repositorio
        List<CategoryInfo> categories = categoryRepository.findAllById(modelToModify.getCategoriesIds());

        //Inyectamos el CategoryInfo a model.categories
        modelToModify.inyectCategoriesFromRespository(categories);

        //Checkeamos la instancia
        modelToModify.checkIstance();

        //Guardar
        Product savedModel = repository.save(modelToModify);

        return savedModel;
    }
    
}
