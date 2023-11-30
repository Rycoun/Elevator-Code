package com.techelevator;


import java.util.Scanner;

class GradeCalculator {

    /**
     * The main method is the start and end of our program
     */
    public static void main(String[] args) {

        /*

            Please provide me with a few of your scores: 1 2 1 3 3

            (1 + 2 + 1 + 3 + 3) / 5

            Your average score is: ___

         */

        System.out.println("Please provide me with a few of your scores: ");

        Scanner keyboard = new Scanner(System.in);

        String userInput = keyboard.nextLine();

        // assume userInput = "1 2 1 3 3"

        String[] splitUpData = userInput.split(" "); // ["1", "2", "1", "3", "3"]

        double sum = 0;

        for (int i = 0; i < splitUpData.length; i++) {

            String currentData = splitUpData[i];

            int currentNum = Integer.parseInt(currentData);

            sum = sum + currentNum;

//            Double.parseDouble();
//            Boolean.parseBoolean();
//            Float.parseFloat()
        }

        double avg = sum / splitUpData.length;

        //   1    2    1    3    3

        System.out.println("Your average score is: " + avg);
    }

}