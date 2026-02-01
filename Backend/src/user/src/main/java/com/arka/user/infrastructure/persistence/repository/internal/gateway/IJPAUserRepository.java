package com.arka.user.infrastructure.persistence.repository.internal.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.user.infrastructure.persistence.entity.UserTable;



@Repository
public interface IJPAUserRepository extends JpaRepository<UserTable,Long> {
    
    boolean existsByUsername(String username);
    boolean existsById(Long id);
}
