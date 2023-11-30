package com.techelevator.challenge;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount {

    public static final BigDecimal LOW_BALANCE = new BigDecimal("150.00");
    public static final BigDecimal SERVICE_CHARGE = new BigDecimal("2.00");
    public SavingsAccount(String accountHolder, String accountNumber, BigDecimal balance) {
        super(accountHolder, accountNumber, balance);
    }

    public SavingsAccount(String accountHolder, String accountNumber) {
        super(accountHolder, accountNumber);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amountToWithdraw) {
        // Only perform transaction if there's still room for service charge
        if (amountToWithdraw.compareTo(BigDecimal.ZERO) > 0 && getBalance().subtract(amountToWithdraw).compareTo(SERVICE_CHARGE) >= 0) {
            // Withdraw the amount
            super.withdraw(amountToWithdraw);

            // Assess service charge if balance goes below low balance threshold
            if (getBalance().compareTo(LOW_BALANCE) < 0) {
                super.withdraw(SERVICE_CHARGE);
            }
        }
        return getBalance();
    }
}
