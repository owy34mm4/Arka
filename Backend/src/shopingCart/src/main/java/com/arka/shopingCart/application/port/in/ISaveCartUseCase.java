package com.arka.shopingCart.application.port.in;

import com.arka.shopingCart.application.usecase.command.SaveShopingCartCommand;

import com.arka.shopingCart.domain.model.ShopingCart;

public interface ISaveCartUseCase {
    ShopingCart execute (SaveShopingCartCommand cmd);
}
