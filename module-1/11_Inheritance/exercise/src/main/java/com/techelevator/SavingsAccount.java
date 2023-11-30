package com.techelevator;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountHolderName, String accountHolderNumber) {
        super (accountHolderName, accountHolderNumber);

    }

    public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        int currentBalance = getBalance();
        int potentialBalance = currentBalance - amountToWithdraw;

        if (potentialBalance > 0) {

            System.out.println("Withdraw request failed");

        } else {
            if (potentialBalance < 150) {
                super.withdraw(2);
            }
            super.withdraw(amountToWithdraw);
        }

        return getBalance();
    }
}
