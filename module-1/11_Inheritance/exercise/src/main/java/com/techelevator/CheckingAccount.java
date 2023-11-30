package com.techelevator;

public class CheckingAccount extends BankAccount {
    private static final double OVERDRAFT_FEE = 10.00;
    public static final double OVERDRAFT_LIMIT = -100;

    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);

    }
    public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);

    }
    @Override
    public int withdraw(int amountToWithdraw) {
        int currentBalance = getBalance();
        int potentialBalance = currentBalance - amountToWithdraw;

        if (potentialBalance > -100) {
            if (potentialBalance >= -100 - 10) {

                super.withdraw(amountToWithdraw);

                if (getBalance() < 0) {
                    super.withdraw(10);

                } else {
                    System.out.println("Withdraw Fail");
                }
            }
        }
        return getBalance();
    }

}
