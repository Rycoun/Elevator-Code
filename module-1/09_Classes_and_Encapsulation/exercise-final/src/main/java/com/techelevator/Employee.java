package com.techelevator;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private double annualSalary;

    public Employee(int employeeId, String firstName, String lastName, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void raiseSalary(double percentage) {
        double raiseAmount = annualSalary * Math.abs(percentage / 100.00);
        annualSalary += raiseAmount;
    }

}
