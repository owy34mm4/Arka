package com.arka.order.application.usecase.handler;



import org.springframework.stereotype.Service;

import com.arka.order.application.port.in.IRegisterOrderUseCase;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;

import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.infoMapper.ShopingCartInfoMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.external.gateway.IShopingCartExternalRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegisterOrderHandler implements IRegisterOrderUseCase{

    private final IShopingCartExternalRepository shopingCartRepository;

    private final IOrderRepository orderRepository;

    private final ShopingCartInfoMapper shopingCartExternalMapper;

    @Override
    public Order execute(RegisterOrderCommand cmd) {
       //Obtener el objeto ShopingCart (Clonar, apagar flag de Ordenada, Guardar)
       ShopingCartInfo sc = shopingCartRepository.findById(cmd.getShopingCartId());

       //Crear Orden a guardar
       Order o = Order.builder()
       .id(null)
       .ownerId(sc.getOwnerId())
       .state(OrderState.PENDIENTE)
       .productsIds(sc.getProductsIds())
       .products(null)
       .build();

       System.out.println(o.toString());

       //Cambiamos la flag del carrito
       ShopingCart scModel = shopingCartExternalMapper.toDomain(sc);
       scModel.setOrdered(true);
       //Guardamos carrio con Flag cambiada
       shopingCartRepository.save(scModel);
        
        //Guardamos la orden
        o = orderRepository.save(o);
        o.setProducts(scModel.getProducts());

        System.out.println(o.toString());


        //Retornar
        return o;

        
    }
    
}
