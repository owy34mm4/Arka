package com.arka.user.application.port.out;

import com.arka.user.domain.model.User;



public interface IUserRepository {
    
    public User save(User user);

    User findById(Long id);

    boolean alreadyExists(String username);

    boolean existsByEmail (String email);

    boolean existsById(Long id);
}
