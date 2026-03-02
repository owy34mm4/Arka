package com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestGenerateWeeklyReport {
    @Email
    private String email;
}
