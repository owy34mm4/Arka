package com.arka.product.application.useCase.handler;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


// import com.arka.category.infrastructure.persistence.repository.external.gateway.ICategoryExternalRepository;
import com.arka.product.application.port.in.IUpdateStockUseCase;

import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.UpdateProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.product.domain.model.ProductHistory;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.category.ICategoryDataPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UpdateStockProductHandler implements IUpdateStockUseCase{

    private final ICategoryDataPort categoryRepository;

    private final IProductRepositoryPort productRepository ;

    private final IProductHistoryRepositoryPort productHisotryRepository;

    private final IUserExternalRepository userRepository;

    
    @Override
    public Product execute(UpdateProductCommand cmd) throws InvalidPropertiesGiven {
        //Validar Permisos de Accion
            if( !( userRepository.isAdmin(cmd.getRequesterId()) || userRepository.isEmploye(cmd.getRequesterId()) ) ){throw new BusinessRuleException("No permitido");}

        //Obtenemos la entidad que vamos a modificar
        Product modelToModify = productRepository.findById(cmd.getProductId());

        //Actualizamos el stock (Validacion en Dominio-ValueObject)
        modelToModify.setStock(new ProductStock(cmd.getNewStock()));
        

        //Persistir

            //Guardar Entidad
            Product savedModel = productRepository.save(modelToModify);       

            //History Record
                ProductHistory pH = savedModel.toProductHistory();

                //Setear MetaData
                    //TimeStamp Modificacion
                    pH.setModifiedAt(Date.from(Instant.now()));
                    //Flag de Responsabilidad
                    pH.setModifiedById(cmd.getRequesterId());
                
                //Persistir ProducthistoryRecord -- No retorna. Solo persiste
                    productHisotryRepository.save(pH);

        //Buscar categorias, con su propio repositorio
        List<CategoryInfo> categories = categoryRepository.findAllById(modelToModify.getCategoriesIds().getValues());

        //Inyectamos el CategoryInfo a model.categories
        savedModel.inyectCategories(categories);

        return savedModel;
    }
    
}
