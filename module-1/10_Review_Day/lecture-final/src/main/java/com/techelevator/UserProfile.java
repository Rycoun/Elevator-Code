package com.techelevator;

import java.time.LocalDate;

public class UserProfile {
    // first name, last name, email, phone number, date of birth
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

    public UserProfile(String fName, String lName, String e, String phone, LocalDate dob) {
        firstName = fName;
        lastName = lName;
        email = e;
        phoneNumber = phone;
        dateOfBirth = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
