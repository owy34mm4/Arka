package com.arka.order.application.usecase.handler;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arka.order.application.port.in.IModifyOrderByCustomerUseCase;

import com.arka.order.application.port.out.IOrderRepository;
import com.arka.order.application.usecase.command.ModifyOrderByCustomerCommand;

import com.arka.order.domain.model.Order;
import com.arka.order.domain.model.enums.OrderState;
import com.arka.product.domain.model.Product;

import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductHistoryMapper;
import com.arka.product.infrastructure.persistence.mapper.adapter.ExternalProductMapper;
import com.arka.shared.application.ports.out.product.IProductDataPort;
import com.arka.shared.application.ports.out.product.IProductHistoryDataPort;
import com.arka.shared.application.ports.out.product.ProductHisotryInfo;
import com.arka.shared.application.ports.out.product.ProductInfo;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.domain.exceptions.BusinessRuleException;
// import com.arka.user.infrastructure.persistence.repository.external.gateway.IUserExternalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModifyOrderByCustomerHandler implements IModifyOrderByCustomerUseCase {

    private final IUserDataPort userRepository;

    private final IOrderRepository orderRepository;

    private final IProductDataPort productRepository;

    private final IProductHistoryDataPort productHistoryExternalRepository;

    private final ExternalProductMapper externalProductMapper;

    private final ExternalProductHistoryMapper externalProductHistoryMapper;

    @Override  
    public Order execute(ModifyOrderByCustomerCommand cmd) {  
  
        // ✅ Carga única de la orden  
        Order o = orderRepository.findById(cmd.getOrderId());  
  
        // Validar permisos  
        if (!o.getOwnerId().equals(cmd.getRequesterId())) {  
            throw new BusinessRuleException("Accion no permitida");  
        }  
        if (!userRepository.existsById(cmd.getRequesterId())) {  
            throw new BusinessRuleException("Accion no permitida");  
        }  
  
        // Validar estado  
        if (o.getState() != OrderState.PENDIENTE) {  
            throw new BusinessRuleException("Solo se puede modificar una Orden pendiente");  
        }  
  
        Order newOrder = cmd.toDomain();  
  
        // Contar productos vieja y nueva orden  
        Map<Long, Long> oldOrderCountById = o.getProductsIds().stream()  
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));  
  
        Map<Long, Long> newOrderCountById = newOrder.getProductsIds().stream()  
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));  
  
        // Validar stock disponible  
        newOrderCountById.forEach((productId, quantity) -> {  
            ProductInfo p = productRepository.findById(productId);  
            if (p.getStock() + oldOrderCountById.getOrDefault(productId, 0L) < quantity) {  
                throw new BusinessRuleException("Stock no Disponible para la compra");  
            }  
        });  
  
        // Liberar stock viejo + historial  
        oldOrderCountById.forEach((productId, quantity) -> {  
            ProductInfo p = productRepository.findById(productId);  
            p.setStock(p.getStock() + Math.toIntExact(quantity));  
            productRepository.save(p);  
  
            // Historial con stock ya actualizado  
            ProductInfo pActualizado = productRepository.findById(productId);  
            Product product = externalProductMapper.toDomain(pActualizado);  
            ProductHisotryInfo pHI = externalProductHistoryMapper.toInfo(product.toProductHistory());  
            pHI.setModifiedAt(Date.from(Instant.now()));  
            pHI.setModifiedById(cmd.getRequesterId());  
            productHistoryExternalRepository.save(pHI);  
        });  
  
        // ✅ Actualizar detalles de la orden usando el helper de la entidad  
        // Esto reemplaza el clear() + addAll() que causaba el wipe  
        o.updateProductsIds(newOrder.getProductsIds());  
  
        // Descontar stock nuevo + historial  
        newOrderCountById.forEach((productId, quantity) -> {  
            ProductInfo p = productRepository.findById(productId);  
            p.setStock(p.getStock() - Math.toIntExact(quantity));  
            productRepository.save(p);  
  
            Product product = externalProductMapper.toDomain(p);  
            ProductHisotryInfo pHI = externalProductHistoryMapper.toInfo(product.toProductHistory());  
            pHI.setModifiedAt(Date.from(Instant.now()));  
            pHI.setModifiedById(cmd.getRequesterId());  
            productHistoryExternalRepository.save(pHI);  
        });  
  
        // ✅ Save único al final  
        o = orderRepository.save(o);  
  
        // Inyección de objetos de respuesta  
        o.setProducts(productRepository.findAllById(cmd.getProductsIds()));
        o.setOwner(userRepository.findById(cmd.getRequesterId()));  
  
        return o;  
    }

}
