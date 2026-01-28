package com.arka.product.infrastructure.api.rest;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.application.useCase.command.UpdateStockCommand;
import com.arka.product.application.useCase.handler.CreateProductHandler;
import com.arka.product.application.useCase.handler.UpdateStockProductHandler;
import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct.RequestCreateProduct;
import com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct.ResponseCreateProduct;
import com.arka.product.infrastructure.api.rest.dto.useCase.CreateProduct.mapper.ApiMapperCreateProduct;
import com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock.RequestUpdateStockProduct;
import com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock.ResponseUpdateStockProduct;
import com.arka.product.infrastructure.api.rest.dto.useCase.UpdateStock.mapper.ApiMapperUpdateStockProduct;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/v0/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final CreateProductHandler createUseCase ;
    private final ApiMapperCreateProduct createProductApiMapper;

    private final UpdateStockProductHandler updateStockUseCase;
    private final ApiMapperUpdateStockProduct updateStockProductApiMapper;
    
     


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCreateProduct createProductRoute(@RequestBody RequestCreateProduct request) throws InvalidPropertiesGiven {
        //Mappeamos a comando
        CreateProductCommand cmd = createProductApiMapper.toCommand(request);
        Product model = createProductApiMapper.toDomain(cmd);
        
        //Ejecutamos el handler del UseCase
        Product savedModel = createUseCase.execute(cmd);

        //Creamos la response
        ResponseCreateProduct response = ResponseCreateProduct.create(
            savedModel.getId(),
            savedModel.getName(),
            savedModel.getDescription(),
            savedModel.getPrice(),
            savedModel.getCategories(),
            "Producto Creado exitosamente");

        //Retornamos la response
        return response;
    }


    @PutMapping("updateStock")
    @ResponseStatus(HttpStatus.OK)
    public ResponseUpdateStockProduct updateProductRoute( @RequestBody RequestUpdateStockProduct request) throws InvalidPropertiesGiven {
        //Mappear el request a command
        //UpdateStockCommand cmd =updateStockProductApiMapper.toCommand(request);
        //cmd.setProductId(productId);
        Product model = updateStockProductApiMapper.to

        //Ejecutamos el handler del UseCase
        Product savedModel = updateStockUseCase.execute(cmd);
        
        ResponseUpdateStockProduct response = ResponseUpdateStockProduct.create(
           savedModel.getId(),
           savedModel.getName(),
           savedModel.getDescription(),
           savedModel.getPrice(),
           savedModel.getStock(),
           savedModel.getCategories(),
           "Stock Actualizado exitosamente"
        );

        return response;
    }
    
    
}
