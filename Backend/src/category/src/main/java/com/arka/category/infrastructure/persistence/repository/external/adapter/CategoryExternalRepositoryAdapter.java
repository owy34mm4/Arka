package com.arka.category.infrastructure.persistence.repository.external.adapter;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.infrastructure.persistence.mapper.ExternalCategoryMapper;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.category.ICategoryDataPort;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryExternalRepositoryAdapter implements ICategoryDataPort {

    private final ICategoryRepository innerRepository;
    private final ExternalCategoryMapper externalCategoryMapper;



    @Override
    public List<CategoryInfo> findAllById(List<Long> productIds) {
        return innerRepository.findAllById(productIds).stream().map(
            c -> externalCategoryMapper.toInfo(c)
        ).toList();
    }

    @Override
    public CategoryInfo findById(Long productId) {
        return externalCategoryMapper.toInfo(innerRepository.findById(productId));
    }

    @Override
    public boolean existsById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public CategoryInfo save(CategoryInfo data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }



    
}
