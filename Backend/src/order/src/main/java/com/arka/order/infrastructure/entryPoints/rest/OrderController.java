package com.arka.order.infrastructure.entryPoints.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.application.usecase.handler.RegisterOrderHandler;
import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.RequestRegisterOrder;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.ResponseRegisterOrder;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v0/order")
@RequiredArgsConstructor
public class OrderController {

    private final  RegisterOrderHandler registerOrderUseCase;

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterOrder> registerOrder(@RequestBody RequestRegisterOrder request) {
        RegisterOrderCommand cmd = RegisterOrderCommand.createFromRequest(request);
        
        Order orderResult =registerOrderUseCase.execute(cmd);
        
        //CrearResponse 

        var response = ResponseRegisterOrder.createFromModel(orderResult, "Exito");

        return ResponseEntity.ok(response);

        
    }
    
    
}
