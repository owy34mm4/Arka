package com.arka.category.application.usecase.handler;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arka.category.application.port.in.ICreateCategoryUseCase;
import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.domain.exception.AlreadyExiststExeption;
import com.arka.category.domain.exception.InvalidIDException;
import com.arka.category.domain.exception.InvalidNameException;
import com.arka.category.domain.model.Category;
import com.arka.category.infrastructure.persistence.repository.adapter.CategoryRepositoryAdapter;;

/**  
 * HANDLER del caso de uso "Crear Producto".  
 *   
 * Responsabilidades:  
 * - Orquestar el flujo de creación  
 * - Coordinar entre dominio y puertos de salida  
 * - Manejar transacciones  
 *   
 * ⚠️ NO contiene lógica de negocio, eso va en Product (Domain)  
 */
@Service
public class CreateCategoryHandler implements ICreateCategoryUseCase {

    @Autowired
    private final CategoryRepositoryAdapter categoryRepository; // application/port/out
    //private final CategoryEventPublisher categoryEventPublisher; // application/port/out

    public CreateCategoryHandler(CategoryRepositoryAdapter categoryRespository){
        this.categoryRepository=categoryRespository;
    }

    @Override
    public Category execute (CreateCategoryCommand command) throws InvalidNameException, InvalidIDException, AlreadyExiststExeption{
        //Validar existencia antes de intentar crear
        if (categoryRepository.existsByName(command.getName())) throw new AlreadyExiststExeption();
        
        //Usar factory method <Create> para encapsular logica y validaciones
        Category category = Category.create(command.getId(), command.getName(), command.getProductsId());

        //Persistir en port/out -> Repository
        Category savedCategory = categoryRepository.save(category);

        //Publicar Eventos de dominio
        //categoryEventPublisher.publishAll();

        //Retornar
        return savedCategory;

    }


}
