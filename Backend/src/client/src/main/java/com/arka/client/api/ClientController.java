package com.arka.client.api;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v0/client/")
public class ClientController {
    
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PostMapping("create")
    public String createProduct(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
}
