package com.example.bankingapi.dto;

import com.example.bankingapi.model.Account;

public class AccountResponse {

    private String id;
    private String name;
    private double balance;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.balance = account.getBalance();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
