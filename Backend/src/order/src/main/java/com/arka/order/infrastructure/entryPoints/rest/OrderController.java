package com.arka.order.infrastructure.entryPoints.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.order.application.port.in.IModifyOrderByCustomerUseCase;

import com.arka.order.application.port.in.IRegisterOrderUseCase;
import com.arka.order.application.usecase.command.ModifyOrderByCustomerCommand;

import com.arka.order.application.usecase.command.RegisterOrderCommand;

import com.arka.order.domain.model.Order;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestModifyOrder;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.ResponseModifyOrder;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.RequestRegisterOrder;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.ResponseRegisterOrder;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v0/order")
@RequiredArgsConstructor
public class OrderController {

    private final  IRegisterOrderUseCase registerOrderUseCase;

    private final IModifyOrderByCustomerUseCase modifyOrderUseCase;

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterOrder> registerOrder(@RequestBody RequestRegisterOrder request) {
        RegisterOrderCommand cmd = RegisterOrderCommand.createFromRequest(request);
        
        Order orderResult =registerOrderUseCase.execute(cmd);
      
        //CrearResponse 
        ResponseRegisterOrder response = ResponseRegisterOrder.createFromModel(orderResult, "Exito");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/modifyByCustomer")
    public ResponseEntity<ResponseModifyOrder> modifyOrderByCustomer( @RequestBody RequestModifyOrder request) {
        ModifyOrderByCustomerCommand cmd = ModifyOrderByCustomerCommand.createFromRequest(request);

        Order orderResult = modifyOrderUseCase.execute(cmd);

        ResponseModifyOrder response = ResponseModifyOrder.createFromModel(orderResult, "Orden Actualizada con Exito");

        return ResponseEntity.ok(response);
    }
    
    
}
