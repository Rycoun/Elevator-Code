package com.techelevator.bankaccount;

public class SavingsAccount extends BankAccount {
    private static final int FEE = 2;
    private static final int MINIMUM_BALANCE = 150;

    public SavingsAccount(String accountHolder, String accountNumber, int balance) {
        super(accountHolder, accountNumber, balance);
    }

    public SavingsAccount(String accountHolder, String accountNumber) {
        super(accountHolder, accountNumber);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        // only perform transaction of positive $ and room for fee
        if (getBalance() - amountToWithdraw >= FEE) {
            super.withdraw(amountToWithdraw);
            // Assess $2 fee if it goes below $150
            if (getBalance() < MINIMUM_BALANCE) {
                super.withdraw(FEE);
            }
        }
        return getBalance();
    }
}
