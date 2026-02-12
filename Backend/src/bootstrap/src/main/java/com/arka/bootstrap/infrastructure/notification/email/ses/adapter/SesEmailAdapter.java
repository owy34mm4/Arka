package com.arka.bootstrap.infrastructure.notification.email.ses.adapter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.arka.bootstrap.infrastructure.notification.email.template.ThymeleafEmailTemplateLoader;
import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@Component
@RequiredArgsConstructor
public class SesEmailAdapter implements IEmailNotificationPort{

    private final SesClient sesClient;
    private final ThymeleafEmailTemplateLoader templateLoader;
    
    @Value ("${cloud-provider.aws.ses.sender}")
    private String senderEmail;

    /**
     * (String to, String subject, String body)
     */
    @Override
    public void send(String to, String subject, String body) {System.out.println("Sendinf email");
        SendEmailRequest request = SendEmailRequest.builder()  
            .destination(d -> d.toAddresses(to))  
            .message(m -> m  
                .subject(s -> s.data(subject).charset("UTF-8"))  
                .body(b -> b.text(t -> t.data(body).charset("UTF-8"))))  
            .source(senderEmail)  
            .build();  
          
        sesClient.sendEmail(request);
    }

    /**
     * Recibe (String to, String subject, String templateName, Map<String, String> variables)
     */
    @Override  
    public void sendHtml(String to, String subject, String templateName, Map<String, Object> variables) {  
        String htmlBody = templateLoader.load(templateName, variables);  
          
        SendEmailRequest request = SendEmailRequest.builder()  
            .source(senderEmail)  
            .destination(d -> d.toAddresses(to))  
            .message(m -> m  
                .subject(s -> s.data(subject).charset("UTF-8"))  
                .body(b -> b.html(h -> h.data(htmlBody).charset("UTF-8"))))  
            .build();  
  
        sesClient.sendEmail(request);  
    }

    
}
