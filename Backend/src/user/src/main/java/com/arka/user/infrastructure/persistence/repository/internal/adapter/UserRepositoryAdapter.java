package com.arka.user.infrastructure.persistence.repository.internal.adapter;


import org.springframework.stereotype.Repository;


import com.arka.shared.domain.exceptions.NotFoundException;
import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.domain.model.User;


import com.arka.user.infrastructure.persistence.mapper.PersistenceUserMapper;
import com.arka.user.infrastructure.persistence.repository.internal.gateway.IJPAUserRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements IUserRepository{

    private final IJPAUserRepository userRepository;

    private final PersistenceUserMapper persistanceUserMapper;
    
    @Transactional
    public User save(User user){
        return persistanceUserMapper.toDomain(
            userRepository.save(
                persistanceUserMapper.toEntity(user)
            )
        );

    }

    
    @Override
    public boolean alreadyExists(String usernameToCheck){
        return userRepository.existsByUsername(usernameToCheck);
    }


    @Override
    public boolean existsById(Long id) {
       return userRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);

    }
    


    @Override
    public User findById(@NonNull Long id) {
        return persistanceUserMapper.toDomain(userRepository.findById(id).orElseThrow(()-> new NotFoundException("User")));
        
    }

    
   




}
