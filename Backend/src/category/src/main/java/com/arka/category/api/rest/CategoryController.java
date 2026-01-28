package com.arka.category.api.rest;


import com.arka.category.api.rest.dto.RequestCreateCategory;
import com.arka.category.api.rest.dto.ResponseCreateCategory;
import com.arka.category.api.rest.mapper.CategoryApiMapper;

import com.arka.category.application.usecase.handler.CreateCategoryHandler;
import com.arka.category.domain.exception.AlreadyExiststExeption;
import com.arka.category.domain.exception.InvalidIDException;
import com.arka.category.domain.exception.InvalidNameException;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v0/category")
public class CategoryController {
    
    private final CreateCategoryHandler createCategoryUseCase;

    private final CategoryApiMapper mapper;

    public CategoryController(CreateCategoryHandler createCategoryUseCase,CategoryApiMapper mapper){
        this.createCategoryUseCase= createCategoryUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseCreateCategory createCategoty(@Valid @RequestBody RequestCreateCategory request) throws InvalidNameException, InvalidIDException, AlreadyExiststExeption{

        // Convertir request a command
        var command = mapper.toCommand(request);

        //Ejecutar Caso de uso
        com.arka.category.domain.model.Category category = createCategoryUseCase.execute(command);


        return ResponseCreateCategory.create(category.getId(), category.getName(), "Categoria Creada con Exito") ;
    }
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
