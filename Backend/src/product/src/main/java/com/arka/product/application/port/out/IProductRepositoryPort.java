package com.arka.product.application.port.out;

import java.util.List;

import com.arka.product.domain.model.Product;
import com.arka.product.infrastructure.persistence.repository.auxiliarObjects.LowStockProductDTO;


public interface IProductRepositoryPort {
    Product save (Product product);
    Product findById (Long id);
    List<Product> findAllById(List<Long> ids);
    boolean existsById(Long id);
    List<LowStockProductDTO> findProductsBelowStock(Integer threshold);
}
