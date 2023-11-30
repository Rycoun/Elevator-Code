package com.techelevator.banking;

import java.util.Scanner;

public class UserInterface {
    private Scanner user = new Scanner(System.in);
    private BankAccountData data = new BankAccountData();

    public void run() {

        while (true) {
            System.out.println("Please choose an option ");
            System.out.println("[1] Deposit");
            System.out.println("[2] Withdraw");
            System.out.println("[3] Exit");

            String userChoice = user.nextLine();

            switch (userChoice) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    System.out.println("Thanks for stopping by!");
                    break;
                default:
                    System.out.println("Wrong option yo");
                    break;
            }
        }
    }
        private void deposit () {
            System.out.println("How much do you want to deposit? ");
            int howMuch = Integer.parseInt(user.nextLine());

            System.out.println("Which account number do you want to use? ");
            String accountNumber = user.nextLine();

            data.deposit(accountNumber, howMuch);
        }
        private void withdraw () {
            System.out.println("How much do you want to withdraw? ");
            int howMuch = Integer.parseInt(user.nextLine());

            System.out.println("Which account number do you want to use? ");
            String accountNumber = user.nextLine();

            data.withdraw(accountNumber, howMuch);
        }
    }

