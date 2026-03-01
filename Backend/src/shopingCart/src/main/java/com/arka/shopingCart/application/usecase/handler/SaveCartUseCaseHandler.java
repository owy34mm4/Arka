package com.arka.shopingCart.application.usecase.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import com.arka.shopingCart.application.port.in.ISaveCartUseCase;
import com.arka.shopingCart.application.port.out.IShopingCartRepository;
import com.arka.shopingCart.application.usecase.command.SaveShopingCartCommand;
import com.arka.shopingCart.domain.model.ShopingCart;



import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveCartUseCaseHandler implements ISaveCartUseCase{

private final IProductDataPort productRepository;

private final IShopingCartRepository shopingCartRepository;

private final IUserDataPort userRepository;


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
            sc.setProductsIds(cmd.getProductsIds());
            
            
        //Persistir
            sc = shopingCartRepository.save(sc);

            //Inyeccion de Objetos de Dominio
            UserInfo owner = userRepository.findById(cmd.getOwnerId());

            sc.setOwner(owner);
            sc.setProducts(productsToOrder);

        return sc;

    }
    
}
