package com.techelevator.bank;

/**
 * CreditCardAccount
 */
public class CreditCardAccount implements Accountable {

    private int debt = 0;
    private final String accountHolderName;
    private final String cardNumber;

    public CreditCardAccount(String accountHolderName, String cardNumber) {
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
    }

    public int pay(int amountToPay) {
        if (amountToPay > 0) {
            debt -= amountToPay;
        }
        return debt;
    }

    public int charge(int amountToCharge) {
        if (amountToCharge > 0) {
            debt += amountToCharge;
        }
        return debt;
    }

    public int getDebt() {
        return debt;
    }

    public int getBalance() {
        return -debt;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }


}
