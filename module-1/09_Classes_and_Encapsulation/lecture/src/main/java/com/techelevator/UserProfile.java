package com.techelevator;

import java.time.LocalDate;
import java.util.PrimitiveIterator;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public UserProfile(String fName, String lName, String e) {
        firstName = fName;
        lastName = lName;
        email = e;

    }

    public String getFirstName() {
        return firstName;

    }
    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getFullName(){
        return firstName + " " + lastName;

    }


}
