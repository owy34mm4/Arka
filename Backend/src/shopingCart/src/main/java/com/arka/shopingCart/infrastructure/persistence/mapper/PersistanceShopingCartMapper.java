package com.arka.shopingCart.infrastructure.persistence.mapper;



import java.util.List;

import org.springframework.stereotype.Component;

import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;
import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartDetailTable;
import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartTable;

@Component
public class PersistanceShopingCartMapper implements IPersistanceMapper<ShopingCart, ShopingCartTable> {

    @Override
    public ShopingCart toDomain(ShopingCartTable sc) {
        if (sc == null) return null;
        return ShopingCart.builder()
            .id(sc.getId())
            .createdAt(sc.getCreatedAt())
            .ownerId(sc.getOwnerId())
            .isOrdered(sc.isOrdered())
            // ✅ Mapear productId del detalle, no su ID
            .productsIds(sc.getDetails().stream()
                .map(ShopingCartDetailTable::getProductId)
                .toList())
            .build();
    }

    @Override
    public ShopingCartTable toEntity(ShopingCart sc) {
        if (sc == null) return null;

        // 1. Construir la entidad padre primero
        ShopingCartTable entity = ShopingCartTable.builder()
            .id(sc.getId())
            .createdAt(sc.getCreatedAt())
            .ownerId(sc.getOwnerId())
            .isOrdered(sc.isOrdered())
            .build();

        // 2. Construir los detalles con referencia al padre
        // ✅ List permite duplicados (ej: [1,1,1,1] → 4 filas)
        List<ShopingCartDetailTable> details = sc.getProductsIds().stream()
            .map(pId -> ShopingCartDetailTable.builder()
                .productId(pId)
                .shopingCartId(entity)  // ✅ FK correcta
                .build())
            .toList();

        entity.setDetails(details);

        return entity;
    }
}
