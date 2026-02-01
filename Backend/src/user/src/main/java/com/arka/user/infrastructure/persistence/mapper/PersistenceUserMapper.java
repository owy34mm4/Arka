package com.arka.user.infrastructure.persistence.mapper;



import org.springframework.stereotype.Component;

import com.arka.shared.infrastructure.persistence.mapper.gateway.IPersistanceMapper;
import com.arka.user.domain.model.User;
import com.arka.user.infrastructure.persistence.entity.UserTable;

@Component
public class PersistenceUserMapper implements IPersistanceMapper< User, UserTable >{

    @Override
    public User toDomain(UserTable c) {
        return User.builder().id(c.getId())
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .firstSurname(c.getFirstSurname())
                    .lastSurname(c.getLastSurname())
                    .username(c.getUsername())
                    .password(c.getPassword())
                    .isActive(c.isActive())
                    .lastLogin(c.getLastLogin())
                    .createdAt(c.getCreatedAt())
                
                .build();
    }

    @Override
    public UserTable toEntity(User c) {
        return UserTable.builder().id(c.getId())
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .firstSurname(c.getFirstSurname())
                    .lastSurname(c.getLastSurname())
                    .username(c.getUsername())
                    .password(c.getPassword())
                    .isActive(c.isActive())
                    .lastLogin(c.getLastLogin())
                    .createdAt(c.getCreatedAt())
                    
                .build();
    }
}
