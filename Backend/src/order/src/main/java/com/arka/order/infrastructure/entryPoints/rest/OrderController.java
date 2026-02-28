package com.arka.order.infrastructure.entryPoints.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.order.application.port.in.IModifyOrderByCustomerUseCase;

import com.arka.order.application.port.in.IRegisterOrderUseCase;
import com.arka.order.application.port.in.IUpdateOrderStatus;
import com.arka.order.application.usecase.command.ModifyOrderByCustomerCommand;

import com.arka.order.application.usecase.command.RegisterOrderCommand;
import com.arka.order.application.usecase.command.UpdateOrderStatusCommand;
import com.arka.order.domain.model.Order;

import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestModifyOrderByCustomer;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestUpdateOrderStatus;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.ResponseModifyOrderByCustomer;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.ResponseUpdateOrderStatus;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.RequestRegisterOrder;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.ResponseRegisterOrder;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v0/order")
@RequiredArgsConstructor
public class OrderController {

    private final IRegisterOrderUseCase registerOrderUseCase;

    private final IModifyOrderByCustomerUseCase modifyOrderUseCase;

    private final IUpdateOrderStatus updateOrderStatusUseCase;

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterOrder> registerOrder(@RequestBody RequestRegisterOrder request) {
        RegisterOrderCommand cmd = RegisterOrderCommand.createFromRequest(request);
        
        Order orderResult =registerOrderUseCase.execute(cmd);
      
        //CrearResponse 
        ResponseRegisterOrder response = ResponseRegisterOrder.createFromModel(orderResult, "Exito");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/modifyByCustomer")
    public ResponseEntity<ResponseModifyOrderByCustomer> modifyOrderByCustomer( @RequestBody RequestModifyOrderByCustomer request) {
        ModifyOrderByCustomerCommand cmd = ModifyOrderByCustomerCommand.createFromRequest(request);

        Order orderResult = modifyOrderUseCase.execute(cmd);

        ResponseModifyOrderByCustomer response = ResponseModifyOrderByCustomer.createFromModel(orderResult, "Orden Actualizada con Exito");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/modifyStatus")
    public ResponseEntity<ResponseUpdateOrderStatus> modifyOrderStatus (@RequestBody RequestUpdateOrderStatus request){
        UpdateOrderStatusCommand cmd = UpdateOrderStatusCommand.createFromRequest(request);

        Order responseModel = updateOrderStatusUseCase.execute(cmd);

        ResponseUpdateOrderStatus response = ResponseUpdateOrderStatus.createFromModel(responseModel, "Status Modificado Exitosamente");

        return ResponseEntity.ok(response);
    }
    
    
}
