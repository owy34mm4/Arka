package com.arka.category.application.usecase.command;

import java.util.Locale.Category;

import org.springframework.stereotype.Service;

import com.arka.category.application.port.in.CreateCategoryCommand;

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

    private final CategoryRepository categoryRepository; // application/port/out
    private final CategoryEventPublisher categoryEventPublisher; // application/port/out

    public CreateCategoryHandler(CategoryRepository categoryRespository, CategoryEventPublisher categoryEventPublisher){
        this.categoryRepository=categoryRespository; this.categoryEventPublisher = categoryEventPublisher;
    }

    @Override
    public Category execute (CreateCategoryCommand command){
        //Validar existencia antes de intentar crear
        if (categoryRepository.existsByName(command.name)) throw new AlreadyExiststExeption();
        
        //Usar factory method <Create> para encapsular logica y validaciones
        Category category = Category.create(command.id, command.name, command.products);

        //Persistir en port/out -> Repository
        Category savedCategory = categoryRepository.save(category);

        //Publicar Eventos de dominio
        categoryEventPublisher.publishAll();

        //Retornar
        return savedCategory;

    }


}
