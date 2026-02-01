package com.arka.shopingCart.application.port.out;

import com.arka.shopingCart.domain.model.ShopingCart;

public interface IShopingCartRepository {
    
    ShopingCart save (ShopingCart sc);
}
