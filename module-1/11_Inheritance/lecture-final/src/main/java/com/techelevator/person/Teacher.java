package com.techelevator.person;

public class Teacher extends Person {
    private String subject;
    private int numberOfStudents;

    public Teacher(String firstName, String lastName, int age, String subject, int numberOfStudents) {
        super(firstName, lastName, age);
        this.subject = subject;
        this.numberOfStudents = numberOfStudents;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
