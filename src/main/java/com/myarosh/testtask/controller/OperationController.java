package com.myarosh.testtask.controller;

import com.myarosh.testtask.model.BankAccount;
import com.myarosh.testtask.service.OperationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    record Data(BankAccount bankAccount, long money){}
    record Transfer(BankAccount fromAccount, BankAccount toAccount, long money){}

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }


    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@Valid @RequestBody Data data){
        System.out.println("Deposit money...");
        long balance = operationService.deposit(data.bankAccount, data.money);
        if (balance == -3L){
            System.out.println("Account not found!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        System.out.println("Money deposited");
        System.out.println("New balance " + balance);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@Valid @RequestBody Data data){
        System.out.println("Withdraw money...");
        long balance = operationService.withdraw(data.bankAccount, data.money);
        if (balance == -3L){
            System.out.println("Account not found!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        if (balance == -2L){
            System.out.println("Account hasn't got right PIN!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        if (balance == -1L){
            System.out.println("Account hasn't got enough money!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        System.out.println("Money withdrew");
        return ResponseEntity.status(HttpStatus.OK).body("");

    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody Transfer transfer){
        System.out.println("Transfer money...");
        long money = operationService.transfer(transfer.fromAccount, transfer.toAccount, transfer.money);
        if (money == -3L){
            System.out.println("Account not found!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        if (money == -2L){
            System.out.println("Account hasn't got right PIN!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        if (money == -1L){
            System.out.println("Account hasn't got enough money!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
        System.out.println("Money transferred");
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
