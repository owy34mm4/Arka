package com.arka.product.infrastructure.persistence.repository.external.gateway;

import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.ProductInfo;

public interface IProductExternalRepository extends IProductDataPort {

    ProductInfo save (ProductInfo product);
    
}
