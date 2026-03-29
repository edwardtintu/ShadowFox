package com.example.bankingapi.model;

import java.util.UUID;

public class Account {

    private final String id;
    private final String name;
    private double balance;

    public Account(String name, double initialBalance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = initialBalance;
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

    // Throws on invalid — NEVER fails silently
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive. Got: " + amount);
        }
        balance += amount;
    }

    // Throws on invalid — NEVER fails silently
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive. Got: " + amount);
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds. Balance: " + balance + ", Requested: " + amount);
        }
        balance -= amount;
    }
}
