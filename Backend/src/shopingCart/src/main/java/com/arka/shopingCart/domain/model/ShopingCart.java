package com.arka.shopingCart.domain.model;

import java.util.Date;
import java.util.List;

import com.arka.client.domain.model.Client;
import com.arka.product.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
@Builder
public class ShopingCart {
    private Long id;
    private Date createdAt;
    private Client owner;
    private List<Product> products;
    
}
