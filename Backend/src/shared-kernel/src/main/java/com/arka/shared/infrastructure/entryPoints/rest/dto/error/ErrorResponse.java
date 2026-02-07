package com.arka.shared.infrastructure.entryPoints.rest.dto.error;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private String statusCode;
    private String message;
    private LocalDateTime timeStamp;
    private String path;
    private Exception exception;

    public static ErrorResponse of(String code, String message, String path, Exception exception) {  
        return ErrorResponse.builder()  
            .statusCode(code)  
            .message(message)  
            .timeStamp(LocalDateTime.now())  
            .path(path)  
            .exception(exception)
            .build();  
    }


}
