package com.arka.category.application.port.in;

import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.domain.model.Category;


public interface ICreateCategoryUseCase {

    Category execute(CreateCategoryCommand cmd) ;
   
}
