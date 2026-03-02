package com.arka.shared.application.ports.out.shoppingCart.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PendingCartDTOInfo {
    private Long cartId;
    private LocalDateTime createdAt;
    private Long ownerId;
    private String firstName;
    private String firstSurname;
    private String email;
    private List<Long> productsIds;
    private List<CartProductInfo> products;
}
