package com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RequestCreateCategory {
    @NotBlank
    String name;
    String x;

}
