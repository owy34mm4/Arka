package com.arka.notifications.domain.model;

public class TopClient {
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
