package com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PendingCartDTO {
    private Long cartId;
    private LocalDateTime createdAt;
    private Long ownerId;
    private String firstName;
    private String firstSurname;
    private String email;
    private List<Long> productsIds;
    private List<CartProduct> products;
}
