package com.arka.shared.infrastructure.infoMapper.gateway;

/**
 * Recibe como parametro en orden
 * 
 * ModelInfo - ModelDomain
 */
public interface IInfoMapper <ModelInfo, ModelDomain>{

    ModelDomain toDomain(ModelInfo info);

    ModelInfo toInfo(ModelDomain domain);
    
}
