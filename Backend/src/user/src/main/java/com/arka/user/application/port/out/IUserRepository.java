package com.arka.user.application.port.out;

import com.arka.user.domain.model.User;

public interface IUserRepository {
    
    public User save(User user);

    boolean alreadyExists(String username);

    boolean existsById(Long id);

}
