package com.arka.shared.infrastructure.notification.email.ses.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.arka.shared.application.ports.out.notification.email.IEmailNotificationPort;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@Component
@RequiredArgsConstructor
public class SesEmailAdapter implements IEmailNotificationPort{

    private final SesClient sesClient;

    @Value ("${cloud-provider.aws.ses.sender}")
    private String senderEmail;

    @Override
    public void send(String to, String subject, String body) {System.out.println("Sendinf email");
        SendEmailRequest request = SendEmailRequest.builder()  
            .destination(d -> d.toAddresses(to))  
            .message(m -> m  
                .subject(s -> s.data(subject))  
                .body(b -> b.text(t -> t.data(body))))  
            .source(senderEmail)  
            .build();  
          
        sesClient.sendEmail(request);
    }

    @Override
    public void sendHtml(String to, String subject, String htmlBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendHtml'");
    }

    
}
