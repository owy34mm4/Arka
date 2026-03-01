package com.arka.shared.application.ports.out.order;

import java.util.List;

import com.arka.shared.application.ports.out.order.dtos.TopCustomerWeeklyDTOInfo;
import com.arka.shared.application.ports.out.order.dtos.TopProductDTOInfo;
import com.arka.shared.domain.gateway.IDataPorts;

public interface IOrderDataPort extends IDataPorts<OrderInfo> {

    List<OrderInfo> findOrderLast7Days();
    TopProductDTOInfo topMasVendido();
    TopCustomerWeeklyDTOInfo topCustomerOfWeek();
}
