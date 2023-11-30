package com.techelevator;
import java.util.Scanner;

public class StoreOpenReporter {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);


        System.out.println("Please Provide Current Hour (in 24 clock): ");
        String input = keyboard.nextLine();
        int currentHour = Integer.parseInt(input);


        System.out.println("Please Provide Current Day (M = Monday)");
        input = keyboard.nextLine();
        char currentDay = input.charAt(0);


        boolean isVaid = true
       do {
        System.out.println("Is it Summer? (Y, N)? ");
        input = keyboard.nextLine();
            boolean isSummer = false;
            char yesOrNo = input.charAt(0);
            if (yesOrNo == 'Y' || yesOrNo == 'y') {
                isSummer = true;
            } else if (yesOrNo == 'N' || yesOrNo == 'n';

            }

    public boolean isStoreOpen(int currentHour, char currentDay, boolean isSummer) {
        if (!isSummer) {
            return currentHour >= 8 && (currentDay == 'M' || currentDay == 'W' || currentDay == 'F') && currentHour < 17;
        } else {
            if (currentDay == 'M' || currentDay == 'F') {
                return currentHour >= 8 && currentHour < 17;
            } else if (currentDay == 'W') {
                return currentHour >= 8 && currentHour < 20;
            } else if (currentDay == 'S') {
                return currentHour >= 9 && currentHour < 15;
            }
        }

        return false;
    }
}
