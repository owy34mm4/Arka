package com.arka.order.infrastructure.persistence.repository.auxiliarObjects;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopCustomerWeeklyDTO {
    Long customerId;  
    String firstName;  
    String firstSurname;  
    String lastName;  
    String lastSurname;  
    long ordersCount;  
    long totalSpent;

    public String getFullName() {  
        return String.join(" ",  
            firstName == null ? "" : firstName,  
            firstSurname == null ? "" : firstSurname,  
            lastName == null ? "" : lastName,  
            lastSurname == null ? "" : lastSurname  
        ).trim().replaceAll("\\s+", " ");  
    }
}
