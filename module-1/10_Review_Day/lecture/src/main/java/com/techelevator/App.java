package com.techelevator;

public class App {

    public static void main(String[] args) {

        UserProfile waltsProfile = new UserProfile("Walt", "Impellicceiri", "walt@techelevator.com");

        UserProfile tomsProfile = new UserProfile("Tom", "Anderson", "tom.anderson@techelevator.com");


        String waltsFirstName = waltsProfile.getFirstName();

        System.out.println(waltsFirstName);

        waltsProfile.setFirstName("Walter");

        waltsFirstName = waltsProfile.getFirstName();

        System.out.println(waltsFirstName);
    }

}
