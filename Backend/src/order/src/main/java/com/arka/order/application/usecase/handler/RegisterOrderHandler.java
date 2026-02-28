package com.arka.order.application.usecase.handler;



import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arka.order.application.port.in.IRegisterOrderUseCase;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.order.domain.valueObjects.OrderSubtotal;
import com.arka.order.domain.valueObjects.OrderTotal;
import com.arka.product.domain.model.Product;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductHistoryMapper;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductMapper;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductExternalRepository;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductHistoryExternalRepository;
import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.shoppingCart.ShopingCartInfo;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.infoMapper.ExternalShopingCartMapper;
import com.arka.shopingCart.infrastructure.persistence.repository.external.gateway.IShopingCartExternalRepository;

// import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegisterOrderHandler implements IRegisterOrderUseCase{

    private final IOrderRepository orderRepository;

    private final IShopingCartExternalRepository shopingCartRepository;

    private final IProductExternalRepository productRepository;

    private final IUserDataPort userRepository;

    private final IProductHistoryExternalRepository productHistoryRepository;


    private final ExternalShopingCartMapper externalShopingCartMapper;

    private final ExternalProductMapper externalProductMapper;

    private final ExternalProductHistoryMapper externalProductHistoryMapper;

    private final IEmailNotificationPort emailPort;


    @Override
    @Transactional
    public Order execute(RegisterOrderCommand cmd) {
        //Validar usuario
            if (!userRepository.existsById(cmd.getRequesterId())){ throw new BusinessRuleException("Solicitante Invalido");}

        //Validar propiedad del carrito
            
            //Obtener el objeto ShopingCart ->  (Clonar, apagar flag de Ordenada, Guardar)
            ShopingCartInfo sc = shopingCartRepository.findById(cmd.getShopingCartId());

            //Validacion del useCase -> Si ya fue ordenado, frenar ejecucion
            if (sc.isOrdered() || sc.getProductsIds().isEmpty()){ System.out.println("SC Ordenado"); throw new BusinessRuleException("Carrito Ya Ordenado o vacio");}
            System.out.println("SC sin ordenar");
            //Validar el OwnerId con el Id del requester
            if (!sc.getOwnerId().equals(cmd.getRequesterId())){ throw new BusinessRuleException("Accion no permitida");}


        //Validar disponibilidad del Stock 
            
            Map<Long, Long> countById = getCountById(sc);


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
            //Map para almacenar los subtotales
            Map<Object, Long> totalPerProduct = new HashMap<>();

            //Logica de Implementacion
                countById.forEach((productId,quantity)->{
                    ProductInfo pInfo = productRepository.findById(productId);
                    Product p = externalProductMapper.toDomain(pInfo);
                    //Validacion en dominio
                    p.setStock( new ProductStock( pInfo.getStock() - Math.toIntExact(quantity) ) );
                    pInfo = externalProductMapper.toInfo(p);

                    //Guardamos que productId tiene que costo -> Si hay 4 productos iguales me guarda el id y el valor de esos 4 -> price*quantity
                    totalPerProduct.put(productId, quantity*p.getPrice().getValue());

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
        //Contar total de dinero
            
            //Implementacion
           int subTotal = totalPerProduct.values().stream()  
            .mapToInt(Math::toIntExact)  
            .sum();

            o.setSubtotal(new OrderSubtotal("COP",subTotal));
            o.setTotal(new OrderTotal("COP", subTotal));
            
        //Persistir
            
            //Guardamos carrito con Flag modificada -> Actualizamos registro viejo
            var scResult = shopingCartRepository.save(scModel);
            System.out.println(scResult.isOrdered());
                
            //Guardamos la orden
            o = orderRepository.save(o);
        
            
        //Retornar

            //Preparar inyeccion para el retorno del objeto
            o.setProducts(productRepository.findAllById(scModel.getProductsIds()) );
            o.setOwner(userRepository.findById(cmd.getRequesterId()));

            UserInfo owner = o.getOwner();

            //Notificar al cliente
                //Generar mapa de variables 
                Map<Object, Object> ownerMap =Map.of(
                    "name",owner.getFirstName()+" "+owner.getFirstSurname(),
                    "email", owner.getEmail()
                );

                Map<String, Object> variablesHtml = new HashMap<>();  
                variablesHtml.put("customer", ownerMap);
                variablesHtml.put("orderId", o.getId());  
                variablesHtml.put("orderStatus", o.getState().name());  
                variablesHtml.put("orderDateTime", LocalDateTime.now());  
                variablesHtml.put("products", o.getProducts());  
                variablesHtml.put("subtotal", o.getSubtotal().getValue());  
                variablesHtml.put("total", o.getTotal().getValue());

            emailPort.sendHtml(o.getOwner().getEmail(), "Orden # "+o.getId()+" Registrada", "orderDetail", variablesHtml );

            return o;

        
    }


    private Map<Long, Long> getCountById(ShopingCartInfo sc) {
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
        return countById;
    }
    
}
