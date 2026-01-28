package com.arka.shared.application.mapper.gateway;

/**
 * Recibe parametros ->
 * DomainModel , Command
 */
public interface ILayerMapper < DMN , CMD > {
    DMN toDomain(CMD command);
    CMD toCommand(DMN modelDomain);
}

