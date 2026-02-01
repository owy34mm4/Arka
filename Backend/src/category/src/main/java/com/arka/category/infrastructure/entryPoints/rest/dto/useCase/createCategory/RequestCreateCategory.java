package com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestCreateCategory {
    @NotBlank
    String name;
    @NotBlank
    Long requester_id;
}
