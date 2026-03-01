package com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGenerateWeeklyReport {
    @NotBlank
    private Long requester_id;
    @Email
    private String email;
}
