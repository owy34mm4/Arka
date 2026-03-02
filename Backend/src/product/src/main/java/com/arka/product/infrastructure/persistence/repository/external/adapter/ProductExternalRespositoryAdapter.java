package com.arka.product.infrastructure.persistence.repository.external.adapter;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductMapper;
import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.ProductInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductExternalRespositoryAdapter implements IProductDataPort {

    private final IProductRepositoryPort innerRepository;

    private final ExternalProductMapper externalProductMapper;



    @Override
    public ProductInfo findById(Long productIds) {
        return externalProductMapper.toInfo(innerRepository.findById(productIds));
    }

    @Override
    public List<ProductInfo> findAllById(List<Long> productsIds) {
       return innerRepository.findAllById(productsIds).stream().map(
        p -> externalProductMapper.toInfo(p)
       ).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return innerRepository.existsById(id);
    }

	@Override
	public ProductInfo save(ProductInfo product) {
        return externalProductMapper.toInfo(
            innerRepository.save(
                externalProductMapper.toDomain(product)
            )
        );

		
	}
    
}
