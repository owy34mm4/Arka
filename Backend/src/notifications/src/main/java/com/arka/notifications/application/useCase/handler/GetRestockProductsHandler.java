package com.arka.notifications.application.useCase.handler;



import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arka.notifications.application.port.in.IGetRestockProductsUseCase;
import com.arka.notifications.application.useCase.command.GetRestockProductsCommand;
import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.dtos.LowStockProductDTOInfo;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetRestockProductsHandler implements IGetRestockProductsUseCase{

    private final IUserDataPort userRepository;

    private final IProductDataPort productRepository;

    private final IEmailNotificationPort notificationAgent;

    @Value ("${cloud-provider.aws.ses.sender}")
    private String senderEmail;

    @Override
    public void execute(GetRestockProductsCommand cmd) {
        //Validar Permisos De accion
            UserInfo requester = userRepository.findById(cmd.getRequesterId());

            if (requester.getRole().name() != Roleinfo.Administrador.name()){throw new BusinessRuleException("Permisos Insuficientes");}

        //Obtener la data de Productos bajops en stock
        List<LowStockProductDTOInfo> productosBajoStock = productRepository.findProductsBelowStock();
        Integer amountLowStock = productosBajoStock.size();

        //Enviar el Reporte
            //Generar Variables
                Map<String,Object> variablesHtml = new HashMap<>();
                variablesHtml.put("recipientName", "Admin");
                variablesHtml.put("reportDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                variablesHtml.put("totalProducts", amountLowStock);
                variablesHtml.put("products", productosBajoStock);
                variablesHtml.put("supportEmail", "info@arka.com");
                variablesHtml.put("currentYear", LocalDateTime.now().getYear());
        
            notificationAgent.sendHtml(senderEmail, "Productos Bajo Stock", "restockProductReport", variablesHtml);
            notificationAgent.sendHtml(cmd.getEmailToSend(), "Productos Bajo Stock", "restockProductReport", variablesHtml);
            
               

            



    }
    
}
