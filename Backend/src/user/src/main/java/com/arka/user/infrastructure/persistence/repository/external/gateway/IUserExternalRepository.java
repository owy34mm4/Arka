package com.arka.user.infrastructure.persistence.repository.external.gateway;

import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.user.domain.model.User;


public interface IUserExternalRepository extends IUserDataPort {
    User save(User u);

    boolean isAdmin(Long id);
    boolean isClient(Long id);
    boolean isEmploye(Long id);
}
