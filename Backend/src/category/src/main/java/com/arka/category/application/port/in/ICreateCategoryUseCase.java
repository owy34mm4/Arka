package com.arka.category.application.port.in;

import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.domain.exception.AlreadyExiststExeption;
import com.arka.category.domain.exception.InvalidIDException;
import com.arka.category.domain.exception.InvalidNameException;
import com.arka.category.domain.model.Category;

public interface ICreateCategoryUseCase {
    /**
     * @param command -> Comando necesario para ejecutar en caso de uso
     * 
     * @return Objeto creado
     * @throws InvalidIDException 
     * @throws InvalidNameException 
     * @throws AlreadyExiststExeption 
     */
    Category execute (CreateCategoryCommand command) throws InvalidNameException, InvalidIDException, AlreadyExiststExeption;

}
