package com.techelevator;


public class CreditCardAccount implements Accountable {

    private String accountHolderName;
    private String cardNumber;
    private int debt;

    public CreditCardAccount(String accountHolderName, String cardNumber) {
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
        this.debt = 0;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getDebt() {
        return debt;
    }

    public int getBalance() {
        return -debt;
    }

    public int pay(int amountPay) {
        if (amountPay > 0 && amountPay <= this.debt) {
            this.debt -= amountPay;

        }
        return this.debt;

    }

    public int charge(int amountCharged) {
        if (amountCharged > 0) {
            this.debt += amountCharged;
        }
        return this.debt;
    }
}
