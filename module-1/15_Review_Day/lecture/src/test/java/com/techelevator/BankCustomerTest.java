package com.techelevator;

import com.techelevator.bank.BankAccount;
import com.techelevator.bank.BankCustomer;
import com.techelevator.bank.CheckingAccount;
import com.techelevator.bank.CreditCardAccount;
import org.junit.Assert;
import org.junit.Test;

public class BankCustomerTest {

    @Test
    public void isVip_returns_false_with_no_accounts() {
        // arrange
        BankCustomer sut = new BankCustomer();

        // act
        boolean isVip = sut.isVip();

        // assert
        Assert.assertFalse(isVip);
    }

    @Test
    public void isVip_returns_false_below_25_000() {
        // arrange
        BankAccount pncChecking = new BankAccount("", "", 30_000);

        CreditCardAccount visa = new CreditCardAccount("", "");
        visa.charge(5_001);

        BankCustomer sut = new BankCustomer();
        sut.addAccount(pncChecking);
        sut.addAccount(visa);

        // act
        boolean isVip = sut.isVip();

        // assert
        Assert.assertFalse(isVip);
    }

    @Test
    public void isVip_returns_true_at_25_000() {
        // arrange
        BankAccount pncChecking = new BankAccount("", "", 30_000);
        CheckingAccount dollarBankChecking = new CheckingAccount("", "", 1);

        CreditCardAccount visa = new CreditCardAccount("", "");
        visa.charge(5_001);

        BankCustomer sut = new BankCustomer();
        sut.addAccount(pncChecking);
        sut.addAccount(visa);
        sut.addAccount(dollarBankChecking);

        // act
        boolean isVip = sut.isVip();

        // assert
        Assert.assertTrue(isVip);
    }

}
