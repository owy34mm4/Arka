package com.arka.shopingCart.infrastructure.entryPoint.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.shopingCart.application.usecase.command.SaveShopingCartCommand;
import com.arka.shopingCart.application.usecase.handler.SaveCartUseCaseHandler;
import com.arka.shopingCart.domain.model.ShopingCart;
import com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart.RequestSaveCart;
import com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart.ResponseSaveCart;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v0/ShopingCart")
@RequiredArgsConstructor
public class ShopingCartController {
    
    private final SaveCartUseCaseHandler saveShopingCartUseCase;

    @PostMapping("/save")
    public ResponseEntity<ResponseSaveCart> saveShopingCart(@RequestBody RequestSaveCart request) {
        //Generamos el command del UseCase
        SaveShopingCartCommand cmd = SaveShopingCartCommand.createFromRequest(request);

        //Ejecutamos el useCase
        ShopingCart scResponse = saveShopingCartUseCase.execute(cmd);
        
        //Creamos la response 
        ResponseSaveCart response = ResponseSaveCart.createFromModel(scResponse, "Exito");
        return ResponseEntity.ok(response);
        
    }
    
}
