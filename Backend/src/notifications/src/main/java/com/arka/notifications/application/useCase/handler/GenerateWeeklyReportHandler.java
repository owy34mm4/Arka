package com.arka.notifications.application.useCase.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.arka.notifications.application.port.in.IGenerateWeeklySaleReportUseCase;
import com.arka.notifications.application.useCase.command.GenerateWeeklyReportCommand;
import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;
import com.arka.shared.application.ports.out.order.IOrderDataPort;
import com.arka.shared.application.ports.out.order.OrderInfo;
import com.arka.shared.application.ports.out.order.dtos.TopCustomerWeeklyDTOInfo;
import com.arka.shared.application.ports.out.order.dtos.TopProductDTOInfo;
import com.arka.shared.application.ports.out.order.enums.OrderStateInfo;
import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GenerateWeeklyReportHandler implements IGenerateWeeklySaleReportUseCase{

    private final IUserDataPort userRepository;

    private final IOrderDataPort orderRepository;

    private final IEmailNotificationPort notificationAgent;

    private final IProductDataPort productRepository;



    @Value ("${cloud-provider.aws.ses.sender}")
    private String senderEmail;


    @Override
    public void execute(GenerateWeeklyReportCommand cmd) {
        //Validamos permisos de Ejecucion
        
            UserInfo requester = userRepository.findById(cmd.getRequesterId());
           
            if(requester.getRole().name() != Roleinfo.Administrador.name()){throw new BusinessRuleException("Permisos insuficientes");}


            String requesterName= requester.getFirstName() + " " + requester.getLastName()+ " " + requester.getFirstSurname();

       
        //Calculamos el reporte

            //Contar cuantas ordenes hay esta semana

                List<OrderInfo> order7Days = orderRepository.findOrderLast7Days();

                Integer amountOfCarts = order7Days.size();

                Integer totalRevenue = order7Days.stream()  
                .map(o -> o.getTotalValue() == null ? 0 : o.getTotalValue())  
                .reduce(0, Integer::sum);

                int pendingOrders = order7Days.stream()
                    .filter(order -> order.getState() == OrderStateInfo.PENDIENTE)
                    .toList().size();

            //Contar el Producto mas vendido

                TopProductDTOInfo productoMasVendido=orderRepository.topMasVendido();

                ProductInfo productoMasVendidoData = productRepository.findById(productoMasVendido.getProductoId());

                Integer totalTopProductRevenue = productoMasVendidoData.getPrice() * productoMasVendido.getTotalVendido();
            
            //Contar el cliente más frecuente

                TopCustomerWeeklyDTOInfo clienteMasFrecuente = orderRepository.topCustomerOfWeek();

                // UserInfo clienteMasFrecuenteData = userRepository.findById(clienteMasFrecuente.getCustomerId());
        
        //Enviamos al sender del notification, y al correo suministrado

            //Mapa identado

                Map<String,Object> topCustomer = new HashMap<>();
                topCustomer.put("name", clienteMasFrecuente.getFullName());
                topCustomer.put("orderCount", clienteMasFrecuente.getOrdersCount());
                topCustomer.put("totalSpent", clienteMasFrecuente.getTotalSpent());


                Map<String,Object> topProduct = new HashMap<>();
                topProduct.put("name", productoMasVendido.getNombre());
                topProduct.put("unitsSold", productoMasVendido.getTotalVendido());
                topProduct.put("revenue", totalTopProductRevenue);

                List<Map<String,Object>> ordersMap = order7Days.stream().map(
                    order-> {
                        Map<String,Object> orderMap= new HashMap<>();
                        orderMap.put("orderId", order.getId());
                        orderMap.put("price", order.getTotalValue());
                        orderMap.put("status", order.getState().name());
                        orderMap.put("timestamp", order.getTimeStamp());
                        return orderMap;
                    }
                ).collect(Collectors.toList());

            Map<String, Object> variablesHtml = new HashMap<>();
            variablesHtml.put("weekStart", "");
            variablesHtml.put("weekEnd", "");
            variablesHtml.put("recipientName", requesterName);
            variablesHtml.put("totalOrders", amountOfCarts);
            variablesHtml.put("totalRevenue", totalRevenue);
            variablesHtml.put("pendingOrders", pendingOrders);
            variablesHtml.put("orders", ordersMap);
            variablesHtml.put("dashboardUrl", "");
            variablesHtml.put("supportEmail", "info@arka.com");
            variablesHtml.put("currentYear", 2026);
            variablesHtml.put("topCustomer", topCustomer);
            variablesHtml.put("topProduct", topProduct);
            
        
            notificationAgent.sendHtml(requester.getEmail(), "Resumen Semanal", "weeklySaleReport", variablesHtml);
            notificationAgent.sendHtml(senderEmail, "Resumen Semanal", "weeklySaleReport", variablesHtml);


        //No retornamos nada
        
    }
    
}
