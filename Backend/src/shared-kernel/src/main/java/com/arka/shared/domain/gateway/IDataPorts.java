package com.arka.shared.domain.gateway;

import java.util.List;

public interface IDataPorts<T> {
    boolean exists(Long Id);
    List<T> findAllById(List<Long> Ids);
    T findById(Long Id);
}
