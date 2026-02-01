package com.arka.user.infrastructure.persistence.repository.internal.adapter;

import org.springframework.stereotype.Repository;

import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.domain.model.User;


import com.arka.user.infrastructure.persistence.entity.UserTable;
import com.arka.user.infrastructure.persistence.mapper.PersistenceUserMapper;
import com.arka.user.infrastructure.persistence.repository.internal.gateway.IJPAUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements IUserRepository{

    private final IJPAUserRepository userRepository;

    private final PersistenceUserMapper persistanceUserMapper;
    
    public User save(User user){
        //Mapear a EntidadPersistencia
        UserTable entity = persistanceUserMapper.toEntity(user);
        
       //Guardamos en BD
        //Sobreescribimos objeto ¿Mejor uso de memoria?
       entity = userRepository.save(entity);
       
       return persistanceUserMapper.toDomain(entity);

    }

    
    @Override
    public boolean alreadyExists(String usernameToCheck){
        return userRepository.existsByUsername(usernameToCheck);
    }


    @Override
    public boolean existsById(Long id) {
       return userRepository.existsById(id);
    }

    
   




}
