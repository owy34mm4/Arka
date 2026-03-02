package com.arka.notifications.infrastructure.entryPoints.rest.dto.getOrphanCarts;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RequestGetOprhanCarts {
    @NotNull
    private Long requester_id;
    @Email
    private String email;
    
    private boolean send_notification;
    
}
