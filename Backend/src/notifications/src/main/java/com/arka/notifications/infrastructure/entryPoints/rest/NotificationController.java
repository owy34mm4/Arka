package com.arka.notifications.infrastructure.entryPoints.rest;

import org.springframework.web.bind.annotation.RestController;

import com.arka.notifications.application.port.in.IGenerateWeeklySaleReportUseCase;
import com.arka.notifications.application.port.in.IGetOrphanCartsUseCase;
import com.arka.notifications.application.port.in.IGetRestockProductsUseCase;
import com.arka.notifications.application.useCase.command.GenerateWeeklyReportCommand;
import com.arka.notifications.application.useCase.command.GetOrphanCartsCommand;
import com.arka.notifications.application.useCase.command.GetRestockProductsCommand;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport.RequestGenerateWeeklyReport;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.WeeklyReport.ResponseGenerateWeeklyReport;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.getOrphanCarts.RequestGetOprhanCarts;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.getOrphanCarts.ResponseGetOrphanCarts;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.getRestockProducts.RequestGetRestockProducts;
import com.arka.notifications.infrastructure.entryPoints.rest.dto.getRestockProducts.ResponseGetRestockProducts;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("api/v0/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final IGenerateWeeklySaleReportUseCase generateWeeklyReportUseCase;

    private final IGetOrphanCartsUseCase getOrphanCartsUseCase;

    private final IGetRestockProductsUseCase getRestockProductsUseCase;


    @PostMapping("/weeklyReport")
    public ResponseEntity<ResponseGenerateWeeklyReport> generateWeeklyReport(@RequestBody RequestGenerateWeeklyReport request) {

        GenerateWeeklyReportCommand cmd = GenerateWeeklyReportCommand.createFromRequest(request);

        generateWeeklyReportUseCase.execute(cmd);

        ResponseGenerateWeeklyReport response = ResponseGenerateWeeklyReport.builder().message("Reporte Enviado exitosamente").build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/orphanCarts")
    public ResponseEntity<ResponseGetOrphanCarts> getOrphanCarts(@RequestBody RequestGetOprhanCarts request) {
       GetOrphanCartsCommand cmd = GetOrphanCartsCommand.createFromRequest(request);

        getOrphanCartsUseCase.execute(cmd);

        ResponseGetOrphanCarts response = ResponseGetOrphanCarts.builder().message("Reporte Enviado").build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/getRestock")
    public ResponseEntity<ResponseGetRestockProducts> getRestock(@RequestBody RequestGetRestockProducts request) {
        GetRestockProductsCommand cmd = GetRestockProductsCommand.createFromRequest(request);

        getRestockProductsUseCase.execute(cmd);
        
        ResponseGetRestockProducts response = ResponseGetRestockProducts.builder().message("Reporte Enviado").build();
        
        return ResponseEntity.ok(response);
    }
    
    
    
    
}
