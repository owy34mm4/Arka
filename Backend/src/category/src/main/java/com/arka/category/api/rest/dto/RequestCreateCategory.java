package com.arka.category.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestCreateCategory {
    @NotBlank
    String name;
}
