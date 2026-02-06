package com.arka.order.application.usecase.handler;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arka.order.application.port.in.IModifyOrderUseCase;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.ModifyOrderCommand;
import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.product.domain.model.Product;

import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductHistoryMapper;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductMapper;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductExternalRepository;
import com.arka.product.infrastructure.persistence.repository.external.gateway.IProductHistoryExternalRepository;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModifyOrderHanlder implements IModifyOrderUseCase {

    private final IUserExternalRepository userRepository;

    private final IOrderRepository orderRepository;

    private final IProductExternalRepository productRepository;

    private final IProductHistoryExternalRepository productHistoryExternalRepository;

    private final ExternalProductMapper externalProductMapper;

    private final ExternalProductHistoryMapper externalProductHisotoryMapper;

    @Override
    public Order execute(ModifyOrderCommand cmd) {
        //Validar permisos de accion
            //Validar existencia del usuario solicitante
            if (! userRepository.existsById(cmd.getRequesterId())){throw new BusinessRuleException("Accion no permitida");}
            //Comprobar que el solicitante sea el dueño de la orden a modificar
            if ( orderRepository.findById(cmd.getOrderId()).getOwnerId() != cmd.getRequesterId() ){throw new BusinessRuleException("Accion no permitida");}
        
        //Obtener la Order Vieja a Modificar
            Order o = orderRepository.findById(cmd.getOrderId());
            System.out.println(o.getState().name());
        
        //Validamos Criterio de Aceptacion ( Solo son modificables en estado PENDIENTE)
            if (o.getState() != OrderState.PENDIENTE){throw new BusinessRuleException("Solo se puede modificar una Orden pendiente ");}


        //Generamos model de la actualziacion de Order
            Order newOrder = cmd.toDomain();
        

         //Validar disponibilidad del Stock en la nueva Orden

            //Contar ocurrencias de cada ID y almacenar en estructuras de datos  // Resultado: {productId1=3, productId2=1, productId5=2}

                //Contar la cantidad de productos en la Orden Vieja
                Map<Long, Long> oldOrderCountById = newOrder.getProductsIds().stream()  
                    .collect(Collectors.groupingBy(  
                                Function.identity(),  
                                Collectors.counting()  
                            ));

            
                //Cntar la cantidad de productos en la Orden Nueva
                Map<Long, Long> newOrderCountById = newOrder.getProductsIds().stream()  
                    .collect(Collectors.groupingBy(  
                                Function.identity(),  
                                Collectors.counting()  
                            ));
                        

            //Logica de Validacion nuevo Stock
                newOrderCountById.forEach((productId, quantity)->{
                    ProductInfo p = productRepository.findById(productId);
                    //Si el stockDisponible actual + el stock que ya está retenido en la Orden Vieja, es menor al nuevo stock de orden. Levanta excepcion
                    if(p.getStock()+oldOrderCountById.get(productId) < quantity){throw new BusinessRuleException("Stock no Disponible para la compra");}
                });

        // Actualizar Orden - Gestionar cambios en stock 

            
            
            //Persistir

                //Liberar Stock antes de Recapturar con la Orden Nueva
                oldOrderCountById.forEach( (productId, quantity)->{
                    ProductInfo p = productRepository.findById(productId);
                    p.setStock(p.getStock() + Math.toIntExact(quantity) );
                    //Guardar nuevo stock
                    p = productRepository.save(p);

                    //ProductHistory Record
                        Product product = externalProductMapper.toDomain(p);
                        ProductHisotryInfo pHI= externalProductHisotoryMapper.toInfo(product.toProductHistory() ) ;
                        //Setear metadatada necesaria
                            //TomeStamp de modificacion
                            pHI.setModifiedAt(Date.from(Instant.now()));
                            //Flag de Responsabilidad
                            pHI.setModifiedById(cmd.getRequesterId());

                            productHistoryExternalRepository.save(pHI);

                });

                //Insertar el nuevo detalle de productos a la instancia de orden vieja
                o.setProductsIds(newOrder.getProductsIds());
                //Actualizar Orden
                o = orderRepository.save(o);


                //Inyeccion de Objetos internos de Orden
                o.setProducts(productRepository.findAllById(cmd.getProductsIds()) );
                o.setOwner(userRepository.findById(cmd.getRequesterId()));

                return o;




                
        
        

    }
    
}
