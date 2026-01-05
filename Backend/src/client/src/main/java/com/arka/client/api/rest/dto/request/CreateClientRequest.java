package com.arka.client.api.rest.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CreateClientRequest {
    private String first_name;
    private String last_name;
    private String first_surname;
    private String last_surname;
    private String username;
    private String password;

    
}
