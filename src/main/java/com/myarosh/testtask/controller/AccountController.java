package com.myarosh.testtask.controller;

import com.myarosh.testtask.model.BankAccount;
import com.myarosh.testtask.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    record LiteAccount(String name, long balance){}

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<LiteAccount>> getAccounts(){
        System.out.println("Getting accounts...");
        List<BankAccount> bankAccounts = accountService.getAccounts();
        List<LiteAccount> liteAccounts = new ArrayList<>();
        bankAccounts.forEach(entry -> liteAccounts.add(new LiteAccount(entry.getName(), entry.getBalance())));
        System.out.println("Accounts got");
        return ResponseEntity.status(HttpStatus.OK).body(liteAccounts);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addAccount(@Valid @RequestBody BankAccount bankAccount){
        System.out.println("Adding new account...");
        System.out.println("Name " + bankAccount.getName());
        System.out.println("PIN " + bankAccount.getPin());
        System.out.println("Balance " + bankAccount.getBalance());
        long id = accountService.addAccount(bankAccount);
        System.out.println("Account added");
        System.out.println("Id " + id);
        if (id != 0) return ResponseEntity.status(HttpStatus.CREATED).body(id);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(id);
    }
}
