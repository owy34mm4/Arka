package com.arka.shopingCart.infrastructure.persistence.repository.external.gateway;


import com.arka.shared.application.ports.out.shoppingCart.IShopingCartDataPort;
import com.arka.shopingCart.domain.model.ShopingCart;

public interface IShopingCartExternalRepository extends IShopingCartDataPort {
    ShopingCart save(ShopingCart sc);
    
}
