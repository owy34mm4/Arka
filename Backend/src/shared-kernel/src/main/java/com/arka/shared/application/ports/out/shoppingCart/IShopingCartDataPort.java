package com.arka.shared.application.ports.out.shoppingCart;

import java.util.List;

import com.arka.shared.application.ports.out.shoppingCart.dtos.PendingCartDTOInfo;
import com.arka.shared.domain.gateway.IDataPorts;


public interface IShopingCartDataPort extends IDataPorts <ShopingCartInfo>{

    List<PendingCartDTOInfo> findOldNotOrderedCartsRaw();
    
}