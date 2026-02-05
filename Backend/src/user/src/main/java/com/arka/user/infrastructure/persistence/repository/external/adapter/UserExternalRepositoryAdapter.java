package com.arka.user.infrastructure.persistence.repository.external.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.NotFoundException;
import com.arka.user.domain.model.User;
import com.arka.user.infrastructure.persistence.entity.UserTable;
import com.arka.user.infrastructure.persistence.entity.enums.Role;
import com.arka.user.infrastructure.persistence.mapper.ExternalUserMapper;
import com.arka.user.infrastructure.persistence.mapper.PersistenceUserMapper;
import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;
import com.arka.user.infrastructure.persistence.repository.internal.gateway.IJPAUserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserExternalRepositoryAdapter implements IUserExternalRepository{

    private final IJPAUserRepository userRepository;
    private final ExternalUserMapper externalUserMapper;
    private final PersistenceUserMapper persistanceUserMapper;

    @Override
    public List<UserInfo> findAllById(List<Long> Ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public UserInfo findById(Long Id) throws NotFoundException {
        return externalUserMapper.toInfo(
            persistanceUserMapper.toDomain(
                userRepository.findById(Id).orElseThrow(()-> new NotFoundException("User"))
            )
        );
    }

    @Override
    public User save(User u) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean existsById(Long id) {
       return userRepository.existsById(id);
    }

    @Override
    public boolean isAdmin(Long id){
       UserTable entity = userRepository.findById(id).orElseThrow(()->new NotFoundException("User"));
       Role entitytRol = entity.getRole();
       return entitytRol==Role.Administrador;
    }

    @Override
    public boolean isClient(Long id){
        UserTable entity = userRepository.findById(id).orElseThrow(()->new NotFoundException("User"));
        Role entitytRol = entity.getRole();
        return entitytRol==Role.Cliente;
    }

    @Override
    public boolean isEmploye(Long id){
        UserTable entity = userRepository.findById(id).orElseThrow(()->new NotFoundException("User"));
        Role entitytRol = entity.getRole();
        return entitytRol==Role.Empleado;
    }


}
