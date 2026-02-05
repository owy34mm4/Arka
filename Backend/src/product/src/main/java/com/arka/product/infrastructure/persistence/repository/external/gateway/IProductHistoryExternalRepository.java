package com.arka.product.infrastructure.persistence.repository.external.gateway;

import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.domain.gateway.IDataPorts;

public interface IProductHistoryExternalRepository extends IDataPorts<ProductHisotryInfo>{
    ProductHisotryInfo save(ProductHisotryInfo pHI);
}
