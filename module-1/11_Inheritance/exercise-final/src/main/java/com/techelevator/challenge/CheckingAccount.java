package com.techelevator.challenge;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {

    public static final BigDecimal MINIMUM_BALANCE = new BigDecimal("-100.00");
    public static final BigDecimal OVERDRAFT_FEE = new BigDecimal("10.00");
    public CheckingAccount(String accountHolder, String accountNumber, BigDecimal balance) {
        super(accountHolder, accountNumber, balance);
    }

    public CheckingAccount(String accountHolder, String accountNumber) {
        super(accountHolder, accountNumber);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amountToWithdraw) {
        // Only allow the withdrawal if the balance won't go below the minimum
        if (amountToWithdraw.compareTo(BigDecimal.ZERO) > 0 && getBalance().subtract(amountToWithdraw).compareTo(MINIMUM_BALANCE) > 0) {
            // Withdraw the amount
            super.withdraw(amountToWithdraw);

            // If the balance goes below 0, assess overdraft fee
            if (getBalance().compareTo(BigDecimal.ZERO) < 0) {
                super.withdraw(OVERDRAFT_FEE);
            }
        }
        return getBalance();
    }
}
