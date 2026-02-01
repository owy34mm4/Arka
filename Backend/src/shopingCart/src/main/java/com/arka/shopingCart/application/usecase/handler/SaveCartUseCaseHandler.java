package com.arka.shopingCart.application.usecase.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shopingCart.application.port.in.ISaveCartUseCase;
import com.arka.shopingCart.application.port.out.IShopingCartRepository;
import com.arka.shopingCart.application.usecase.command.SaveShopingCartCommand;
import com.arka.shopingCart.domain.model.ShopingCart;

import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveCartUseCaseHandler implements ISaveCartUseCase{

private final IProductDataPort productRepository;

private final IShopingCartRepository shopingCartRepository;

private final IUserExternalRepository userRepository;


    @Override
    public ShopingCart execute(SaveShopingCartCommand cmd) {

        //Mapemos de ProductIds a ProductsInfos
        List<ProductInfo> productsToOrder = productRepository.findAllById(cmd.getProductsIds());
       
        //Obtenemos el Owner
        UserInfo owner = userRepository.findById(cmd.getOwnerId());

        ShopingCart sc = cmd.toDomain(cmd);

        sc = shopingCartRepository.save(sc);

        sc.setProducts(productsToOrder);
        sc.setOwner(owner);

        return sc;

    }
    
}
