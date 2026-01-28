package com.arka.shared.infrastructure.api.mapper.gateway;


/**
Receives kind of "parameters" in next order ->

Command , Request 
 */
public interface IApiMapper <CMD, RQST > {
    /*
    La sintaxis refiere a que -> Dentro de los signos " < > " se citan todos los objetos genericos del contrato 
    separados por coma. Deben ser pasados como "parametros" al implementar 
    */
    CMD toCommand(RQST request);
}
