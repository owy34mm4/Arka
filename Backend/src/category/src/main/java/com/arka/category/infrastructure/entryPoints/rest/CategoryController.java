package com.arka.category.infrastructure.entryPoints.rest;


import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.application.usecase.handler.CreateCategoryHandler;
import com.arka.category.domain.model.Category;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.RequestCreateCategory;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.ResponseCreateCategory;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v0/category")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CreateCategoryHandler createCategoryUseCase;


    @PostMapping("/create")
    public ResponseEntity<ResponseCreateCategory> createCategoty( @RequestBody RequestCreateCategory request) throws InvalidPropertiesGiven {
       //Generar el command
       var cmd = CreateCategoryCommand.createFromRequest(request);

       //Ejecutar el useCase
       Category modelResponse = createCategoryUseCase.execute(cmd);

       //Construir Respuesta
       var response = ResponseCreateCategory.createFromModel(modelResponse, "Exitoso");

       return ResponseEntity.ok(response);
    }
    
}
