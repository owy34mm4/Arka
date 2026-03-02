package com.arka.notifications.application.useCase.command;

import com.arka.notifications.infrastructure.entryPoints.rest.dto.getRestockProducts.RequestGetRestockProducts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetRestockProductsCommand {
    private String emailToSend;

    public static GetRestockProductsCommand createFromRequest(RequestGetRestockProducts request){
        return GetRestockProductsCommand.builder()
            .emailToSend(request.getEmail_to_Send())
        .build();
    }
}
