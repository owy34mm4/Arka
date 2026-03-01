package com.arka.notifications.infrastructure.entryPoints.rest;

import org.springframework.web.bind.annotation.RestController;

import com.arka.notifications.application.port.in.IGenerateWeeklySaleReportUseCase;
import com.arka.notifications.application.useCase.command.GenerateWeeklyReportCommand;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport.RequestGenerateWeeklyReport;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport.ResponseGenerateWeeklyReport;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("api/v0/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final IGenerateWeeklySaleReportUseCase generateWeeklyReportUseCase;

    @PostMapping("/weeklyReport")
    public ResponseEntity<ResponseGenerateWeeklyReport> generateWeeklyReport(@RequestBody RequestGenerateWeeklyReport request) {

        GenerateWeeklyReportCommand cmd = GenerateWeeklyReportCommand.createFromRequest(request);

        generateWeeklyReportUseCase.execute(cmd);

        ResponseGenerateWeeklyReport response = ResponseGenerateWeeklyReport.builder().message("Reporte Enviado exitosamente").build();

        return ResponseEntity.ok(response);
    }
    
    
}
