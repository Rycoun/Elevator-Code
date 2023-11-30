package com.techelevator;

import com.techelevator.bank.BankAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {

    private static final int STARTING_BALANCE = 100;
    private BankAccount sut;
    @Before
    public void setup() {
        sut = new BankAccount("", "", STARTING_BALANCE);
    }


    @Test
    public void deposit_is_not_accepted_given_negative() {
        // arrange
//        BankAccount sut = new BankAccount("", "");

        // act
        int resultingBalance = sut.deposit(-10);

        // assert
        Assert.assertEquals("The balance returned by the deposit method should be " + STARTING_BALANCE, STARTING_BALANCE, resultingBalance);
        Assert.assertEquals("The balance returned by the deposit method should match getBalance", STARTING_BALANCE, sut.getBalance());
    }

    @Test
    public void deposit_is_accepted_given_positive() {
        // arrange
//        BankAccount sut = new BankAccount("", "", 100);

        // act
        int resultingBalance = sut.deposit(10);

        // assert
        Assert.assertEquals("The balance returned by the deposit method should be 110", 110, resultingBalance);
        Assert.assertEquals("The balance returned by the deposit method should match getBalance", 110, sut.getBalance());
    }


    @Test
    public void transferFunds_rejects_transaction_given_null_destination() {
        // arrange
        BankAccount destination = null;

        // act
        int myResultingBalance = sut.transferFunds(destination, 10);

        // assert
        Assert.assertEquals(STARTING_BALANCE, myResultingBalance);
        Assert.assertEquals(STARTING_BALANCE, sut.getBalance());
    }

}
