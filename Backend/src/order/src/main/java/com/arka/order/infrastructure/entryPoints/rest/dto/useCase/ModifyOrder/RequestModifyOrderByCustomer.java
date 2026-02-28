package com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder;

import java.util.List;

import lombok.Getter;

@Getter
public class RequestModifyOrderByCustomer {
    private Long requester_id;

    private Long order_id;

    private List<Long> products_ids;

}
