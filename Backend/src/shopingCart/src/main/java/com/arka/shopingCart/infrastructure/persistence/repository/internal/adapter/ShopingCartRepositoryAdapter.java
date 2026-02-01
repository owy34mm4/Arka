package com.arka.shopingCart.infrastructure.persistence.repository.internal.adapter;

import org.springframework.stereotype.Repository;

import com.arka.shopingCart.application.port.out.IShopingCartRepository;
import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.persistence.mapper.PersistanceShopingCartMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.internal.gateway.IJPAShopingCart;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShopingCartRepositoryAdapter implements IShopingCartRepository {

    private final PersistanceShopingCartMapper persistanceShopingCartMapper;
    private final IJPAShopingCart shopingCartRepository;


    @Override
    public ShopingCart save(ShopingCart sc) {
        var scT = persistanceShopingCartMapper.toEntity(sc);
        scT = shopingCartRepository.save(scT);
        return persistanceShopingCartMapper.toDomain(scT);
    }
    
}
