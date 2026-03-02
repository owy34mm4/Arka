package com.arka.shopingCart.infrastructure.persistence.repository.external.adapter;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.arka.shared.application.ports.out.shoppingCart.IShopingCartDataPort;
import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.shared.application.ports.out.shoppingCart.dtos.PendingCartDTOInfo;

import com.arka.shopingCart.application.port.out.IShopingCartRepository;

import com.arka.shopingCart.infrastructure.infoMapper.ExternalShopingCartMapper;
import com.arka.shopingCart.infrastructure.persistence.mapper.ExternalPendingCartDTOMapper;


import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShopingCartExternalRepositoryAdapter implements IShopingCartDataPort{


    private final IShopingCartRepository innerRepository;

    private final ExternalShopingCartMapper externalShopingCartMapper;

    private final ExternalPendingCartDTOMapper externalPendingCartMapper;

 

    @Override
    public ShopingCartInfo findById(Long Id) {
       return externalShopingCartMapper.toInfo(innerRepository.findById(Id));

    }


    @Override
    public boolean existsById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public ShopingCartInfo save(ShopingCartInfo data) {
       return externalShopingCartMapper.toInfo(
            innerRepository.save(
                externalShopingCartMapper.toDomain(data)
            )
        );
    }

    @Override
    public List<ShopingCartInfo> findAllById(List<Long> Ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }


    @Override
    public List<PendingCartDTOInfo> findOldNotOrderedCartsRaw() {
        return innerRepository.findOldNotOrderedCartsRaw().stream().map(
            cart -> externalPendingCartMapper.toInfo(cart)
        ).toList();
        
    }
    
}
