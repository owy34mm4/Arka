package com.arka.shopingCart.infrastructure.persistence.repository.internal.adapter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.arka.shared.domain.exceptions.NotFoundException;
import com.arka.shopingCart.application.port.out.IShopingCartRepository;
import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.persistence.mapper.PersistanceShopingCartMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects.PendingCartDTO;
import com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects.PendingCartDTOMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.internal.gateway.IJPAShopingCart;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShopingCartRepositoryAdapter implements IShopingCartRepository {

    private final PersistanceShopingCartMapper persistanceShopingCartMapper;
    private final IJPAShopingCart shopingCartRepository;
    private final PendingCartDTOMapper pendingCartMapper;


    @Override
    public ShopingCart save(ShopingCart sc) {
        var scT = persistanceShopingCartMapper.toEntity(sc);
        scT = shopingCartRepository.save(scT);
        return persistanceShopingCartMapper.toDomain(scT);
    }


    @Override
    public List<PendingCartDTO> findOldNotOrderedCartsRaw() {
        return pendingCartMapper.fromRawRows(
            shopingCartRepository.findOldNotOrderedCartsRaw(
                LocalDateTime.now().minusDays(3)
            ).orElseThrow(()-> new NotFoundException("OrphanCarts"))
        );
    }


    @Override
    public ShopingCart findById(Long id) {
        return persistanceShopingCartMapper.toDomain(
            shopingCartRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("ShopingCart")
            )
        );
    }

    
    
}
