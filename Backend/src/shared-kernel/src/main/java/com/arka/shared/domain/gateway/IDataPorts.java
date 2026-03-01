package com.arka.shared.domain.gateway;

import java.util.List;

/**
 *Recibe una entidadInfo
 */
public interface IDataPorts<Info> {
    Info save(Info data);
    boolean existsById(Long Id);
    List<Info> findAllById(List<Long> Ids);
    Info findById(Long Id);
}
