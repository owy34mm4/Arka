package com.arka.notifications.application.useCase.command;

import com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport.RequestGenerateWeeklyReport;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerateWeeklyReportCommand {
    private String emailToSend;

    public static GenerateWeeklyReportCommand createFromRequest(RequestGenerateWeeklyReport request){
        return GenerateWeeklyReportCommand.builder()
        .emailToSend(request.getEmail())
        .build();
    }
}
