package com.arka.notifications.infrastructure.entryPoints.rest.dto.getRestockProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetRestockProducts {
    private Long requester_id;
    private String email_to_Send;
    
}
