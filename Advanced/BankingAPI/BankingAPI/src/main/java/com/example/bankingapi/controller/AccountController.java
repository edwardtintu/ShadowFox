package com.example.bankingapi.controller;

import com.example.bankingapi.dto.AccountResponse;
import com.example.bankingapi.dto.CreateAccountRequest;
import com.example.bankingapi.dto.TransactionRequest;
import com.example.bankingapi.model.Account;
import com.example.bankingapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    // POST /accounts
    // Body: { "name": "Edward", "initialBalance": 1000 }
    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody CreateAccountRequest request) {
        Account acc = service.createAccount(request.getName(), request.getInitialBalance());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountResponse(acc));
    }

    // GET /accounts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String id) {
        Account acc = service.getAccount(id);
        return ResponseEntity.ok(new AccountResponse(acc));
    }

    // POST /accounts/{id}/deposit
    // Body: { "amount": 500 }
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable String id,
            @RequestBody TransactionRequest request) {
        Account acc = service.deposit(id, request.getAmount());
        return ResponseEntity.ok(new AccountResponse(acc));
    }

    // POST /accounts/{id}/withdraw
    // Body: { "amount": 200 }
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable String id,
            @RequestBody TransactionRequest request) {
        Account acc = service.withdraw(id, request.getAmount());
        return ResponseEntity.ok(new AccountResponse(acc));
    }
}
