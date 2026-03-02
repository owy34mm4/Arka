package com.arka.shopingCart.application.port.out;

import java.util.List;

import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects.PendingCartDTO;

public interface IShopingCartRepository {
    
    ShopingCart save (ShopingCart sc);

    ShopingCart findById(Long id);

    List<PendingCartDTO> findOldNotOrderedCartsRaw();
}
