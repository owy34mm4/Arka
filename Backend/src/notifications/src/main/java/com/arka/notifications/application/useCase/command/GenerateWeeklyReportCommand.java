package com.arka.notifications.application.useCase.command;

import com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport.RequestGenerateWeeklyReport;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerateWeeklyReportCommand {
    private Long requesterId;
    private String emailToSend;

    public static GenerateWeeklyReportCommand createFromRequest(RequestGenerateWeeklyReport request){
        return GenerateWeeklyReportCommand.builder()
        .requesterId(request.getRequester_id())
        .emailToSend(request.getEmail())
        .build();
    }
}
