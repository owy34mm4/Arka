package com.arka.product.application.port.out;


import com.arka.product.domain.model.ProductHistory;

import com.arka.product.infrastructure.persistence.entity.ProductTable;

public interface IProductHistoryPort {
    ProductHistory save(ProductTable productToRecord);
    ProductHistory findById(Long id);
    
}
