package com.techelevator.person;

public class Chef extends Person {
    private String specialty;
    private int yearsOfExperience;

    public Chef(String firstName, String lastName, int age, String specialty, int yearsOfExperience) {
        super(firstName, lastName, age);

        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Chef(String specialty, int yearsOfExperience) {
        super();

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
