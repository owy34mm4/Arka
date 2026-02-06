package com.arka.order.application.usecase.handler;



import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arka.order.application.port.in.IRegisterOrderUseCase;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductHistoryMapper;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductMapper;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductExternalRepository;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductHistoryExternalRepository;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.infoMapper.ExternalShopingCartMapper;
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

    private final IProductHistoryExternalRepository productHistoryRepository;


    private final ExternalShopingCartMapper externalShopingCartMapper;

    private final ExternalProductMapper externalProductMapper;

    private final ExternalProductHistoryMapper externalProductHistoryMapper;

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

            //Contar ocurrencias de cada ID  // Resultado: {productId1=3, productId2=1, productId5=2}
            Map<Long, Long> countById = sc.getProductsIds().stream()  
                .collect(Collectors.groupingBy(  
                            Function.identity(),  
                            Collectors.counting()  
                        ));
                        
            //Logica de Validacion
            countById.forEach((productId, quantity)->{
                ProductInfo p = productRepository.findById(productId);
                if(p.getStock()<quantity){throw new BusinessRuleException("Stock no Disponible para la compra");}
            });


        //Crear Dominio 

            //Dump del Comand a Model
            Order o = cmd.toModel();
            //Seteos de data del UseCase
                o.setState(OrderState.PENDIENTE);
                o.setProductsIds(sc.getProductsIds());

            //Convertimos de Info a Domain
            ShopingCart scModel = externalShopingCartMapper.toDomain(sc);
            //Cambiamos la flag del carrito
            scModel.setOrdered(true);

            
        //Reducir Stock > Reducir el Stock de los productos ( Restar la cantidad ordenada al stock)
            
            //Logica de Implementacion
                countById.forEach((productId,quantity)->{
                    ProductInfo pInfo = productRepository.findById(productId);
                    Product p = externalProductMapper.toDomain(pInfo);
                    //Validacion en dominio
                    p.updateStock(pInfo.getStock() - Math.toIntExact(quantity) );
                    pInfo = externalProductMapper.toInfo(p);

                    //Persistir(Actualizar Stock) - Persistir ProductHistory Record
                        productRepository.save(pInfo);

                        ProductHisotryInfo pHI= externalProductHistoryMapper.toInfo(p.toProductHistory());
                        //Set de Metadata
                            //TimeStamp de Actualizacion
                            pHI.setModifiedAt(Date.from(Instant.now()));
                            //Flag de Responsabilidad
                            pHI.setModifiedById(cmd.getRequesterId());

                        productHistoryRepository.save(pHI);
                        
                });
            
        //Persistir

            //Guardamos carrito con Flag modificada -> Actualizamos registro viejo
            shopingCartRepository.save(scModel);
                
            //Guardamos la orden
            o = orderRepository.save(o);

        //Retornar

            //Preparar inyeccion para el retorno del objeto
            o.setProducts(productRepository.findAllById(scModel.getProductsIds()) );
            o.setOwner(userRepository.findById(cmd.getRequesterId()));

            return o;

        
    }
    
}
