package com.arka.shared.infrastructure.externalMapper.gateway;

/**
 * Recibe como parametros. En orden
 * 
 * ModelInfo - ModelDomain
 */
public interface IExternalMapper <ModelInfo, ModelDomain>{

    ModelDomain toDomain(ModelInfo info);

    ModelInfo toInfo(ModelDomain domain);
    
}
