package com.techelevator;
import java.util.Scanner;


class GradeCalculator {

    /**
     * The main method is the start and end of our program
     * 1, 2, 1, 3, 3
     */
    public static void main(String[] args) {
        System.out.println("Provide Scores: ");
        Scanner keyboard = new Scanner(System.in);

        String user = keyboard.nextLine();
        String[] splitData = user.split(" ");

        double  sum = 0;

        for(int i = 0; i <splitData.length; i++) {
            String data = splitData[i];
            int currentNumber = Integer.parseInt(data);
            sum = sum + currentNumber;
        }
        double avd = sum / splitData.length;

        System.out.println("Your adv Score is: " + avd);


    }

}