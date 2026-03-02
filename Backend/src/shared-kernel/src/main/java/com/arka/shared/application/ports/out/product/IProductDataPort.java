package com.arka.shared.application.ports.out.product;



import java.util.List;

import com.arka.shared.application.ports.out.product.dtos.LowStockProductDTOInfo;
import com.arka.shared.domain.gateway.IDataPorts;


public interface IProductDataPort extends IDataPorts <ProductInfo>{
    List<LowStockProductDTOInfo> findProductsBelowStock ();
}
  
