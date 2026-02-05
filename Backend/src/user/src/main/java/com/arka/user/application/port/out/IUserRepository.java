package com.arka.user.application.port.out;

import com.arka.user.domain.model.User;



public interface IUserRepository {
    
    public User save(User user);

    boolean alreadyExists(String username);

    boolean existsByEmail (String email);

    boolean existsById(Long id);

    boolean isAdmin(Long id);
    
    boolean isClient(Long id);

    boolean isEmploye(Long id);
}
