package com.arka.shared.domain.exceptions;

/**
 * Recibe un String message
 *  Ej -> new BussinesRuleViolation("Usuario ya existe") returns -> "BUINESS_RULE_VIOLATION Usuario ya existe"
 */
public class BusinessRuleException extends DomainException {

    public BusinessRuleException(String message) {
        super("BUSINESS_RULE_VIOLATION", message);
    }
    
}
