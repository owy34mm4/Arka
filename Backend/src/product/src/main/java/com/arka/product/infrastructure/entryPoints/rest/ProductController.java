package com.arka.product.infrastructure.entryPoints.rest;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.application.useCase.command.UpdateProductCommand;
import com.arka.product.application.useCase.handler.CreateProductHandler;
import com.arka.product.application.useCase.handler.UpdateStockProductHandler;
import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.RequestCreateProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.ResponseCreateProduct;

import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.RequestUpdateStockProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.ResponseUpdateStockProduct;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v0/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final CreateProductHandler createUseCase ;

    private final UpdateStockProductHandler updateStockUseCase;
   


    @PostMapping("/create")
    public ResponseEntity<ResponseCreateProduct> createProductRoute(@RequestBody RequestCreateProduct request) throws InvalidPropertiesGiven {
        //Crear command
        var cmd = CreateProductCommand.createFromRequest(request);

        //Ejecutamos el handler del UseCase
        Product savedModel = createUseCase.execute(cmd);

        //Creamos la response
        ResponseCreateProduct response = ResponseCreateProduct.createFromModel(savedModel, "Producto Creado exitosamente");

        
        return ResponseEntity.ok(response);
    }


    @PutMapping("updateStock")
    public ResponseEntity<ResponseUpdateStockProduct> updateProductRoute( @RequestBody RequestUpdateStockProduct request) throws InvalidPropertiesGiven {
        
        //Ejecutamos nuestro caso de uso, inyectando el resultado de la creacion del command a traves de la request
        Product savedModel = updateStockUseCase.execute(UpdateProductCommand.createFromRequest(request));
        
        //Creamos respuesta 
        ResponseUpdateStockProduct response = ResponseUpdateStockProduct.createFromModel(savedModel,"Stock Actualizado exitosamente");

        return ResponseEntity.ok(response);
    }
    
    
}
