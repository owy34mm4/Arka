package com.arka.product.application.port.out;


import com.arka.product.domain.model.ProductHistory;



public interface IProductHistoryRepositoryPort {
    ProductHistory save(ProductHistory productToRecord);
    ProductHistory findById(Long id);
    
}
