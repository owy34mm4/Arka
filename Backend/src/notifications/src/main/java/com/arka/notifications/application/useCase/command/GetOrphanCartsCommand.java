package com.arka.notifications.application.useCase.command;

import com.arka.notifications.infrastructure.entryPoints.rest.dto.getOrphanCarts.RequestGetOprhanCarts;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetOrphanCartsCommand {
    private Long requesterId;
    private String receiverEmail;
    private boolean sendNotification;

    public static GetOrphanCartsCommand createFromRequest(RequestGetOprhanCarts request){
        return GetOrphanCartsCommand.builder()
        .requesterId(request.getRequester_id())
        .receiverEmail(request.getEmail())
        .sendNotification(request.isSend_notification())
        .build();
    }
}
