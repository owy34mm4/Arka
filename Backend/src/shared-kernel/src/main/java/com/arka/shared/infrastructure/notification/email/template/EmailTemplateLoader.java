package com.arka.shared.infrastructure.notification.email.template;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.arka.shared.domain.exceptions.EmailSendingException;

@Component
public class EmailTemplateLoader {

    public String load(String templateName, Map<String, String> variables) {  
        try {  
            String template = new String(  
                getClass().getResourceAsStream("/templates/email/" + templateName + ".html")  
                    .readAllBytes(),  
                StandardCharsets.UTF_8  
            );  
              
            for (Map.Entry<String, String> entry : variables.entrySet()) {  
                template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());  
            }  
              
            return template;  
        } catch (IOException e) {  
            throw new EmailSendingException("Error al cargar la Plantilla HTML -> " + templateName+".html");  
        }  
    }

    
}
