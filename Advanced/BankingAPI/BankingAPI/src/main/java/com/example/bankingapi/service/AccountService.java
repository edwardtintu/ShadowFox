package com.example.bankingapi.service;

import com.example.bankingapi.exception.AccountNotFoundException;
import com.example.bankingapi.model.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {

    // Thread-safe — ConcurrentHashMap replaces HashMap
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public Account createAccount(String name, double initialBalance) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Account name must not be blank.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        Account account = new Account(name, initialBalance);
        accounts.put(account.getId(), account);
        return account;
    }

    public Account getAccount(String id) {
        Account acc = accounts.get(id);
        if (acc == null) throw new AccountNotFoundException(id);
        return acc;
    }

    public Account deposit(String id, double amount) {
        Account acc = getAccount(id);
        acc.deposit(amount);   // throws IllegalArgumentException if invalid
        return acc;
    }

    public Account withdraw(String id, double amount) {
        Account acc = getAccount(id);
        acc.withdraw(amount);  // throws IllegalArgumentException if invalid/insufficient
        return acc;
    }
}
