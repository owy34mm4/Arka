package com.arka.category.application.port.in;

import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.domain.model.Category;

public interface ICreateCategoryUseCase {
    /**
     * @param command -> Comando necesario para ejecutar en caso de uso
     * 
     * @return Objeto creado
     */
    Category execute (CreateCategoryCommand command);

}
