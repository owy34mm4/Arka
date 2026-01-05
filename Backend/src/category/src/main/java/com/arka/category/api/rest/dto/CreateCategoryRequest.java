package com.arka.category.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCategoryRequest {
    @NotBlank
    String name;
}
