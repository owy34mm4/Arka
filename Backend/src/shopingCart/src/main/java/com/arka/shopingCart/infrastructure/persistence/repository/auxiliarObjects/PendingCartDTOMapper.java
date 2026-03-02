package com.arka.shopingCart.infrastructure.persistence.repository.auxiliarObjects;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PendingCartDTOMapper {
    public List<PendingCartDTO> fromRawRows(List<Object[]> rows) {  
        return rows.stream()  
            .collect(Collectors.groupingBy(r -> (Long) r[0]))  // agrupar por cartId  
            .entrySet().stream()  
            .map(entry -> {  
                Object[] first = entry.getValue().get(0);  // datos del carrito (iguales en todas las filas)  
  
                List<Long> productIds = entry.getValue().stream()  
                    .map(r -> (Long) r[6])  
                    .toList();  
  
                return PendingCartDTO.builder()  
                    .cartId(         (Long)          first[0])  
                    .createdAt(      (LocalDateTime) first[1])  
                    .ownerId(        (Long)          first[2])  
                    .firstName(      (String)        first[3])  
                    .firstSurname(   (String)        first[4])  
                    .email(          (String)        first[5])  
                    .productsIds(    productIds                )  
                    .build();  
            })  
            .toList();  
    }
}
