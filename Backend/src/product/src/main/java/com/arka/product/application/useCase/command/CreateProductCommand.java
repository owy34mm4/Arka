package com.arka.product.application.useCase.command;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class CreateProductCommand {
    String name;
    String description;
    Integer price;
    Integer stock;
    List<Long> categories;
}
