package com.arka.order.application.usecase.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.arka.order.application.port.in.IUpdateOrderStatus;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.UpdateOrderStatusCommand;
import com.arka.order.domain.model.Order;
import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateOrderStatusHandler implements IUpdateOrderStatus{

    private final IOrderRepository orderRepository;

    private final IUserDataPort userRepository;

    private final IProductDataPort productRepository;

    private final IEmailNotificationPort notificationAgent;
    
    @Override
    public Order execute(UpdateOrderStatusCommand cmd) {
        //Validamos criterios de ejecucion

            //Solicitante tenga privilegios de ejecucion
                UserInfo requester = userRepository.findById(cmd.getRequesterId());

                if (requester.getRole()== Roleinfo.Cliente) { throw new BusinessRuleException("Permisos Insuficientes");}

            //Orden si es del dueño

                Order oldOrder = orderRepository.findById(cmd.getOrderId());
                UserInfo maybeOwner = userRepository.findById(cmd.getOwnerId());

                if(oldOrder.getOwnerId() != maybeOwner.getId()){throw new BusinessRuleException("Orden Ajena");}

        
        //Ejecutamos Actualizacion - Usamos instancias creadas en validacion

            Order commandAlive = cmd.toModel();


            oldOrder.setState(commandAlive.getState());

            //Persistimos
                oldOrder = orderRepository.save(oldOrder);
        
        
        //Notificamos al cliente

            //Generar mapa de variables 
                Map<Object, Object> ownerMap =Map.of(
                    "name",maybeOwner.getFirstName()+" "+maybeOwner.getFirstSurname(),
                    "email",maybeOwner.getEmail()
                );

                Map< String,Object> variablesHtml= new HashMap<>();
                variablesHtml.put("customer", ownerMap);
                variablesHtml.put("orderId", oldOrder.getId());
                variablesHtml.put("orderStatus", oldOrder.getState().name());
                variablesHtml.put("orderDateTime", LocalDateTime.now());
                variablesHtml.put("products", productRepository.findAllById(new ArrayList<>(oldOrder.getProductsIds())));
                variablesHtml.put("subtotal", oldOrder.getSubtotal().getValue());
                variablesHtml.put("total", oldOrder.getTotal().getValue());
                


           notificationAgent.sendHtml(maybeOwner.getEmail(), "Actualizacion de Estado en Orden", "orderDetail", variablesHtml);

           

        return oldOrder;
    }


}