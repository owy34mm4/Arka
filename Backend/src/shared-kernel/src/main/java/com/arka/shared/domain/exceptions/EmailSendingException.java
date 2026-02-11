package com.arka.shared.domain.exceptions;

/**
 * Recibe como parametro un mensaje que da informacion del momento de fallo 
 * 
 * ej-> mew EmailSendingException("No se puedo cargar la Plantilla") -> "ERROR_WHILE_SENDING_EMAIL No se puedo cargar la Plantilla"
 */
public class EmailSendingException extends DomainException{

    public EmailSendingException(String message) {
        super("ERROR_WHILE_SENDING_EMAIL ", message);
        
    }
    
}
