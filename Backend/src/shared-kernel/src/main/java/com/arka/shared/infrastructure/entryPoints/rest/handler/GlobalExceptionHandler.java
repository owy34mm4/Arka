package com.arka.shared.infrastructure.entryPoints.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.arka.shared.domain.exceptions.BusinessRuleException;
import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;
import com.arka.shared.domain.exceptions.NotFoundException;
import com.arka.shared.infrastructure.entryPoints.rest.dto.error.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(InvalidPropertiesGiven.class)
    public ResponseEntity<ErrorResponse> handleInvalidPropertiesGiven(
        InvalidPropertiesGiven ex,
        HttpServletRequest request
    ){
        ErrorResponse error = ErrorResponse.of(
            ex.getCode(),
            ex.getMessage(),
            request.getRequestURI(),
            ex
            );

        return ResponseEntity.status(HttpStatusCode.valueOf(426)).body(error);
    }


    
    @ExceptionHandler(NotFoundException.class)  
    public ResponseEntity<ErrorResponse> handleNotFound(  
            NotFoundException ex,   
            HttpServletRequest request
        ){  
        ErrorResponse error = ErrorResponse.of(  
            ex.getCode(),  
            ex.getMessage(),  
            request.getRequestURI(),
            ex

        );  
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);  
    }  

      

    @ExceptionHandler(BusinessRuleException.class)  
    public ResponseEntity<ErrorResponse> handleBusinessRule(  
            BusinessRuleException ex,  
            HttpServletRequest request) {  
          
        ErrorResponse error = ErrorResponse.of(  
            ex.getCode(),  
            ex.getMessage(),  
            request.getRequestURI(),
            ex
        );  
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);  
    }  


      
    @ExceptionHandler(Exception.class)  
    public ResponseEntity<ErrorResponse> handleGeneric(  
            Exception ex,  
            HttpServletRequest request) {  
          
        ErrorResponse error = ErrorResponse.of(  
            "INTERNAL_ERROR",  
            "Error interno del servidor",  
            request.getRequestURI(),
            ex
        );  
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);  
    }

}
