package com.arka.product.application.port.out;

import com.arka.product.domain.model.Product;


public interface IProductRepositoryPort {
    Product save (Product product);
    Product findById (Long id);
}
