package com.arka.user.infrastructure.persistence.repository.external.adapter;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.UserInfo;

import com.arka.user.application.port.out.IUserRepository;
import com.arka.user.infrastructure.persistence.mapper.ExternalUserMapper;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserExternalRepositoryAdapter implements IUserDataPort{


    private final IUserRepository innerRepository;
    private final ExternalUserMapper externalUserMapper;


    

    @Override
    public UserInfo findById(@NonNull Long Id) {
        return externalUserMapper.toInfo(
                innerRepository.findById(Id)
        );
    }



    @Override
    public boolean existsById(Long id) {
       return innerRepository.existsById(id);
    }


    @Override
    public UserInfo save(UserInfo data) {
       return  externalUserMapper.toInfo(
            innerRepository.save(
                externalUserMapper.toDomain(data)
            )
        );
    }

    @Override
    public List<UserInfo> findAllById(List<Long> Ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }
    



}
