package com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestGenerateWeeklyReport {
    @NotNull
    private Long requester_id;
    @Email
    private String email;
}
