package com.arka.product.application.useCase.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arka.product.application.port.in.IUpdateStockUseCase;

import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.UpdateStockProductCommand;
import com.arka.product.domain.model.Product;
import com.arka.product.domain.model.ProductHistory;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.category.ICategoryDataPort;
import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UpdateStockProductHandler implements IUpdateStockUseCase{

    private final ICategoryDataPort categoryRepository;

    private final IProductRepositoryPort productRepository ;

    private final IProductHistoryRepositoryPort productHisotryRepository;

    private final IUserDataPort userRepository;

    private final IAuthenticateUserPort authenticateUserPort;

    private boolean isAdminOrEmployee(UserInfo requester){
       return requester.getRole().name().equals(Roleinfo.Administrador.name()) || requester.getRole().name().equals(Roleinfo.Empleado.name());
    }
    
    @Override
    public Product execute(UpdateStockProductCommand cmd) throws InvalidPropertiesGiven {
        //Validar Permisos de Accion
            var requesterId = authenticateUserPort.getUserId();
            UserInfo requester = userRepository.findById(requesterId);
            
            if(!isAdminOrEmployee(requester)){throw new BusinessRuleException("No permitido");}

        //Obtenemos la entidad que vamos a modificar
            Product modelToModify = productRepository.findById(cmd.getProductId());

            //Actualizamos el stock (Validacion en Dominio-ValueObject)
            modelToModify.updateStock(cmd.getNewStock());

        //Persistir

            //Guardar Entidad
            Product savedModel = productRepository.save(modelToModify);       

            //History Record
                ProductHistory pH = savedModel.toProductHistory();

                //Setear MetaData
                    //TimeStamp Modificacion & Flag de Responsabilidad
                    pH.establishForModification(requester.getId());
                    
                //Persistir ProducthistoryRecord -- No retorna. Solo persiste
                    productHisotryRepository.save(pH);

        //Buscar categorias, con su propio repositorio
        List<CategoryInfo> categories = categoryRepository.findAllById(savedModel.getCategoriesIds().getValues());
        

        //Inyectamos el CategoryInfo a model.categories
        savedModel.inyectCategories(categories);
        

        return savedModel;
    }
    
}
