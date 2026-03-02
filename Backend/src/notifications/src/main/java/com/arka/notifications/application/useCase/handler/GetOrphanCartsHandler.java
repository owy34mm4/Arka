package com.arka.notifications.application.useCase.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.arka.notifications.application.port.in.IGetOrphanCartsUseCase;
import com.arka.notifications.application.useCase.command.GetOrphanCartsCommand;
import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.shoppingCart.IShopingCartDataPort;
import com.arka.shared.application.ports.out.shoppingCart.dtos.CartProductInfo;
import com.arka.shared.application.ports.out.shoppingCart.dtos.PendingCartDTOInfo;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetOrphanCartsHandler implements IGetOrphanCartsUseCase{

    private final IShopingCartDataPort shopingcartRepository;

    private final IUserDataPort userRepository;
    
    private final IEmailNotificationPort notificationAgent;

    private final IProductDataPort productRepository;

    @Override
    public void execute(GetOrphanCartsCommand cmd) {
        //Validamos permisos de Accion
            UserInfo requester = userRepository.findById(cmd.getRequesterId());

            if(requester.getRole().name() != Roleinfo.Administrador.name()){throw new BusinessRuleException("Accion no permitida");}

        
        // Obtenemos los Carritos Abandonados

            List<PendingCartDTOInfo> carritosHuerfanos = shopingcartRepository.findOldNotOrderedCartsRaw();  
    
            List<PendingCartDTOInfo> carritosEnriquecidos = carritosHuerfanos.stream()  
                .peek(cart -> {  
                    List<CartProductInfo> products = productRepository.findAllById(cart.getProductsIds())  
                        .stream()  
                        .map(p -> CartProductInfo.builder()  
                            .productId(p.getId())  
                            .name(p.getName())  
                            .price(p.getPrice())  
                            .currency(p.getCurrency())  
                            .build()  
                        )  
                        .toList();  
                    cart.setProducts(products);  
                })  
                .toList();
            
            //Contar cantidad
                Integer amountOfOrphans = carritosHuerfanos.size();

        

        //Notificamos 
                //Generamos mapa variablesHtml
                    Map<String,Object> variablesHtml= new HashMap<>();
                    variablesHtml.put("recipientName", requester.getFirstName());
                    variablesHtml.put("reportDate", LocalDateTime.now());
                    variablesHtml.put("totalAbandonedCarts", amountOfOrphans );
                    variablesHtml.put("pendingCarts", carritosEnriquecidos);
                    variablesHtml.put("supportEmail", "info@arka.com");
                    variablesHtml.put("currentYear", LocalDateTime.now().getYear());
                    
            //Enviar Reporte al Sender y el Correo brindado
            notificationAgent.sendHtml(requester.getEmail(), "Reporte Carritos Abandonados", "orphanCarts", variablesHtml);

            //Notificar a los dueños de los carros abandonados, si se desea
                if ( cmd.isSendNotification()){

                    carritosEnriquecidos.stream().forEach(
                        cart -> {
                            UserInfo ownerData= userRepository.findById(cart.getOwnerId());
                            Map<String,Object> variablesNotificacionHtml = new HashMap<>();
                            variablesNotificacionHtml.put("firstName", ownerData.getFirstName());
                            variablesNotificacionHtml.put("cartCreatedAt", cart.getCreatedAt());
                            variablesNotificacionHtml.put("products", cart.getProducts());
                            // variablesNotificacionHtml.put("cartTotal", null );
                            variablesNotificacionHtml.put("supportEmail", "info@arka.com");
                            variablesNotificacionHtml.put("currentYear", LocalDateTime.now().getYear());


                            notificationAgent.sendHtml(ownerData.getEmail(), "Recordatorio Carrito #"+cart.getCartId(), "orphanCartsReminder", variablesNotificacionHtml);

                            
                        }
                    );
                }

    }
    
}
