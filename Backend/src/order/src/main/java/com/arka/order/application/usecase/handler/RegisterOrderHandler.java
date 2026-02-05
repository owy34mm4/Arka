package com.arka.order.application.usecase.handler;



import org.springframework.stereotype.Service;

import com.arka.order.application.port.in.IRegisterOrderUseCase;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductExternalRepository;
import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.infoMapper.ShopingCartInfoMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.external.gateway.IShopingCartExternalRepository;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegisterOrderHandler implements IRegisterOrderUseCase{

    private final IOrderRepository orderRepository;

    private final IShopingCartExternalRepository shopingCartRepository;

    private final IProductExternalRepository productRepository;

    private final IUserExternalRepository userRepository;

    private final ShopingCartInfoMapper shopingCartExternalMapper;

    @Override
    public Order execute(RegisterOrderCommand cmd) {
        //Validar usuario

            if (!userRepository.existsById(cmd.getRequesterId())){ throw new BusinessRuleException("Solicitante Invalido");}

        //Validar propiedad del carrito
        
            //Obtener el objeto ShopingCart ->  (Clonar, apagar flag de Ordenada, Guardar)
            ShopingCartInfo sc = shopingCartRepository.findById(cmd.getShopingCartId());

            //Validacion del useCase -> Si ya fue ordenado, frenar ejecucion
            if (sc.isOrdered() == true){throw new BusinessRuleException("Carrito Ya Ordenado");}

            //Validar el OwnerId con el Id del requester
            if (sc.getOwnerId()!=cmd.getRequesterId()){ throw new BusinessRuleException("Accion no permitida");}

        //Validar disponibilidad del Stock



        //Crear Dominio

            //Dump del Comand a Model
            Order o = cmd.toModel();
            //Seteos de data del UseCase
            o.setState(OrderState.PENDIENTE);
            o.setProductsIds(sc.getProductsIds());

            //Convertimos de Info a Domain
            ShopingCart scModel = shopingCartExternalMapper.toDomain(sc);
            //Cambiamos la flag del carrito
            scModel.setOrdered(true);

        //Persistir

            //Guardamos carrito con Flag modificada -> Actualizamos registro viejo
            shopingCartRepository.save(scModel);
                
            //Guardamos la orden
            o = orderRepository.save(o);

        //Retornar

            //Preparar inyeccion para el retorno del objeto
            o.setProducts(scModel.getProducts());

            return o;

        
    }
    
}
