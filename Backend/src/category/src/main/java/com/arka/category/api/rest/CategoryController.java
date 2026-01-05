package com.arka.category.api.rest;


import com.arka.category.api.rest.dto.CreateCategoryRequest;
import com.arka.category.api.rest.dto.mapper.CategoryApiMapper;
import com.arka.category.application.port.in.ICreateCategoryUseCase;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    private final ICreateCategoryUseCase createCategoryUseCase;

    private final CategoryApiMapper mapper;

    public CategoryController(ICreateCategoryUseCase createCategoryUseCase,CategoryApiMapper mapper){
        this.createCategoryUseCase= createCategoryUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<String> createCategoty(@Valid @RequestBody CreateCategoryRequest request){

        // Convertir request a command
        var command = mapper.toCommand(request);

        //Ejecutar Caso de uso
        com.arka.category.domain.model.Category category = createCategoryUseCase.execute(command);

        //Construir respuesta HTTP  
        URI location = URI.create("/api/v1/products/" + category.getId());

        return ResponseEntity.created(location).body("Created Sucesfully");
    }
}
