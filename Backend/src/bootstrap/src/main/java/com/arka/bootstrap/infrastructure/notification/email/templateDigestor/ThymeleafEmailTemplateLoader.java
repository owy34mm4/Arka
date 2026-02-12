package com.arka.bootstrap.infrastructure.notification.email.templateDigestor;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

//Reminder -> Al parecer esta libreria se apoya mucho en la estructura suministrada por el HTML, no hay validaciones antes o despues. Solo inyecte y ponga. Muy propensa al fallo, pero muy util -> Requiere Test
@Component
@RequiredArgsConstructor
public class ThymeleafEmailTemplateLoader {
    
    private final SpringTemplateEngine templateEngine;  
  
    public String load(String templateName, Map<String, Object> variables) {  
        Context context = new Context();  
        context.setVariables(variables);  
        return templateEngine.process("email/" + templateName.replace(".html", ""), context);  
    }
}
