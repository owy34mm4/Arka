package com.arka.category.infrastructure.entryPoints.rest;


import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.application.usecase.handler.CreateCategoryHandler;
import com.arka.category.domain.model.Category;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.RequestCreateCategory;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.ResponseCreateCategory;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v0/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Gestion de Categorias")
public class CategoryController {
    
    private final CreateCategoryHandler createCategoryUseCase;

    @Operation(summary = "Crear Categoria")
    @PostMapping("/create")
    public ResponseEntity<ResponseCreateCategory> createCategoty( @RequestBody RequestCreateCategory request) throws InvalidPropertiesGiven, NotFoundException {
       //Generar el command
       var cmd = CreateCategoryCommand.createFromRequest(request);

       //Ejecutar el useCase
       Category modelResponse = createCategoryUseCase.execute(cmd);

       //Construir Respuesta
       var response = ResponseCreateCategory.createFromModel(modelResponse, "Exitoso");

       return ResponseEntity.ok(response);
    }
    
}
