package com.arka.shopingCart.application.usecase.handler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductExternalRepository;

import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import com.arka.shopingCart.application.port.in.ISaveCartUseCase;
import com.arka.shopingCart.application.port.out.IShopingCartRepository;
import com.arka.shopingCart.application.usecase.command.SaveShopingCartCommand;
import com.arka.shopingCart.domain.model.ShopingCart;

import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveCartUseCaseHandler implements ISaveCartUseCase{

private final IProductExternalRepository productRepository;

private final IShopingCartRepository shopingCartRepository;

private final IUserExternalRepository userRepository;


    @Override
    public ShopingCart execute(SaveShopingCartCommand cmd) {
        //Validar Usuario Proporcionado
            if(!userRepository.existsById(cmd.getOwnerId())){ throw new BusinessRuleException("No autorizado"); }

        //Mapemos de ProductIds a ProductsInfos
        List<ProductInfo> productsToOrder = productRepository.findAllById(cmd.getProductsIds());

        //Validar Disponibilidad del Stock

            //Contar ocurrencias de cada ID  // Resultado: {productId1=3, productId2=1, productId5=2}
            Map<Long, Long> countById = cmd.getProductsIds().stream()  
                .collect(Collectors.groupingBy(  
                            Function.identity(),  
                            Collectors.counting()  
                        ));
                        
            //Logica de Validacion
            countById.forEach((productId, quantity)->{
                ProductInfo p = productRepository.findById(productId);
                if(p.getStock()<quantity){throw new BusinessRuleException("Stock no Disponible para la compra");}
            });


        //Crear Dominio
            ShopingCart sc = cmd.toDomain(cmd);

        //Persistir
            sc = shopingCartRepository.save(sc);

        //Inyeccion de Objetos de Dominio
        sc.setProducts(productsToOrder);
        UserInfo owner = userRepository.findById(cmd.getOwnerId());
        sc.setOwner(owner);

        return sc;

    }
    
}
