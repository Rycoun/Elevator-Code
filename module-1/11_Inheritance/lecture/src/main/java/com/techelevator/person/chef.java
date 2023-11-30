package com.techelevator.person;

public class chef extends person {
    private String specialty;
    private int yearsOfExperience;

    public chef(String specialty, int yearsOfExperience, String firstName, String lastName, int age) {
        super(firstName, lastName, age);

        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;


    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
