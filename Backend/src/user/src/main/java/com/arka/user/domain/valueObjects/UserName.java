package com.arka.user.domain.valueObjects;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserName {
    private String firstName;
    private String lastName;
    private String firstSurname;
    private String lastSurname;

    public static UserName create (String firstName, String lastName, String firstSurname, String lastSurname){
        if (firstName.isBlank() || firstSurname.isBlank()) {throw new InvalidPropertiesGiven("UserName");}
        return UserName.builder()
        .firstName(firstName)
        .lastName(lastName)
        .firstSurname(firstSurname)
        .lastSurname(lastSurname)
        .build();
    }

    public String getFulName(){
        return getFirstName()+" "+getLastName()+" "+getFirstSurname()+" "+getLastSurname();
    }
}
