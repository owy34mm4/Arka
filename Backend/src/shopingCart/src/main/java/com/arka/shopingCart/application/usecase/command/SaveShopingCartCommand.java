package com.arka.shopingCart.application.usecase.command;

import java.util.List;

import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart.RequestSaveCart;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SaveShopingCartCommand {
    Long id;
    Long ownerId;
    List<Long> productsIds;
    

    public static SaveShopingCartCommand createFromRequest(RequestSaveCart request){
        return SaveShopingCartCommand.builder()
            .id(null)
            .ownerId(request.getOwner_id())
            .productsIds(request.getProducts_ids())
        .build();
    }

    public ShopingCart toDomain(SaveShopingCartCommand cmd){
        return ShopingCart.builder()
            .id(null)
            .ownerId(this.getOwnerId())
            .productsIds(productsIds)
            .products(null)
        .build();
    }
}
