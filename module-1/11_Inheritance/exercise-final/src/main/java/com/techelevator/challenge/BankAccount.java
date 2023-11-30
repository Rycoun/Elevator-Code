package com.techelevator.challenge;

import java.math.BigDecimal;

public class BankAccount {

    private String accountHolderName;
    private String accountNumber;
    private BigDecimal balance;

    public BankAccount(String accountHolder, String accountNumber) {
        this.accountHolderName = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
    }

    public BankAccount(String accountHolder, String accountNumber, BigDecimal balance) {
        this.accountHolderName = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal deposit(BigDecimal amountToDeposit) {
        if (amountToDeposit.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amountToDeposit);
        }
        return balance;
    }

    public BigDecimal withdraw(BigDecimal amountToWithdraw) {
        if (amountToWithdraw.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.subtract(amountToWithdraw);
        }
        return balance;
    }

}
