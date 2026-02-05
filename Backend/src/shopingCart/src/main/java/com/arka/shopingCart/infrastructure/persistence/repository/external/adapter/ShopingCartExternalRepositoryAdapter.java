package com.arka.shopingCart.infrastructure.persistence.repository.external.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.shared.domain.exceptions.NotFoundException;
import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.infoMapper.ExternalShopingCartMapper;
import com.arka.shopingCart.infrastructure.persistence.mapper.PersistanceShopingCartMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.external.gateway.IShopingCartExternalRepository;
import com.arka.shopingCart.infrastructure.persistence.repository.internal.gateway.IJPAShopingCart;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShopingCartExternalRepositoryAdapter implements IShopingCartExternalRepository {

    private final IJPAShopingCart shopingCartRepository;

    private final PersistanceShopingCartMapper persistanceShopingCartMapper;

    private final ExternalShopingCartMapper shopingCartInfoMapper;

 
    @Override
    public List<ShopingCartInfo> findAllById(List<Long> Ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public ShopingCartInfo findById(Long Id) {
       var entity=shopingCartRepository.findById(Id).orElseThrow(()->new NotFoundException("ShopingCart "));
       var model =persistanceShopingCartMapper.toDomain(entity);
       return shopingCartInfoMapper.toInfo(model);

    }

    @Override
    public ShopingCart save(ShopingCart sc) {
       return persistanceShopingCartMapper.toDomain(
        shopingCartRepository.save(
            persistanceShopingCartMapper.toEntity(sc)
        )
    );
    }

    @Override
    public boolean existsById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }
    
}
