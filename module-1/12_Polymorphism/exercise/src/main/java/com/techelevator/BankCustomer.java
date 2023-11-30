package com.techelevator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class BankCustomer {
    private String name;
    private String address;
    private String phoneNumber;
    private List<Accountable> accounts;

    public BankCustomer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();

    }

    public Accountable[] getAccounts() {
        return accounts.toArray(new Accountable[0]);
    }
    public void addAccount(Accountable newAccount) {
        accounts.add(newAccount);
    }
    public boolean isVip() {
        int totalMoney = 0;
        for (Accountable account : accounts) {
            totalMoney += account.getBalance();
        }
        return totalMoney >= 25000;
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
}


