package com.techelevator;

import java.util.ArrayList;
import java.util.List;

/**
 * BankCustomer
 */
public class BankCustomer {

    private String name;
    private String address;
    private String phoneNumber;
    private List<Accountable> accounts = new ArrayList<>();

    public void addAccount(Accountable account) {
        accounts.add(account);
    }

    public Accountable[] getAccounts() {
        return accounts.toArray(new Accountable[0]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isVip() {
        int total = 0;
        for (Accountable account : accounts) {
            total += account.getBalance();
        }
        return total >= 25000;
    }
}
