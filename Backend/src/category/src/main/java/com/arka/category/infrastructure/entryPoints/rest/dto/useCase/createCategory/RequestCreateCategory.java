package com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestCreateCategory {
    @NotBlank
    String name;

}
