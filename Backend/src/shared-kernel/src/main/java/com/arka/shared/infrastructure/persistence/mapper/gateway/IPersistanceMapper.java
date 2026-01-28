package com.arka.shared.infrastructure.persistence.mapper.gateway;
/**
 * Recibe los parametro -> ModelDomain , EntityPersistance
 */
public interface IPersistanceMapper < DMN , ENT > {
    DMN toDomain(ENT entity);
    ENT toEntity (DMN model);
    
}
