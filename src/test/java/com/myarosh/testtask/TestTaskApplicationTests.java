package com.myarosh.testtask;

import com.myarosh.testtask.model.BankAccount;
import com.myarosh.testtask.service.AccountService;
import com.myarosh.testtask.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestTaskApplicationTests {

    @Autowired
    OperationService operationService;
    @Autowired
    AccountService accountService;
    BankAccount bankAccount = new BankAccount(1L, "Ivan", 123, "0123");
    int balance = 123;

    @Test
    void test1AddAccount() {
        assertEquals(accountService.addAccount(new BankAccount(0, "Ivan", 0, "0123")), 1);
    }

    @Test
    void test2Deposit() {
        assertEquals(operationService.deposit(new BankAccount(2L, null, 0, null), balance), -3L);
        assertEquals(operationService.deposit(new BankAccount(1L, null, 0, null), balance), balance);
    }

    @Test
    void test3Withdraw() {
        assertEquals(operationService.withdraw(new BankAccount(2L, null, 0, null), 1000), -3L);
        assertEquals(operationService.withdraw(new BankAccount(1L, null, 0, null), 1000), -2L);
        assertEquals(operationService.withdraw(new BankAccount(1L, null, 0, "0123"), 1000), -1L);
        assertEquals(operationService.withdraw(new BankAccount(1L, null, 0, "0123"), 12), balance-12);
    }

    @Test
    void test4Transfer(){
        accountService.addAccount(new BankAccount(0, "Vasya", 0, "4321"));
        assertEquals(operationService.transfer(new BankAccount(3L, null, 0, null),
                new BankAccount(2L, null, 0, null), 1000), -3L);
        assertEquals(operationService.transfer(new BankAccount(1L, null, 0, null),
                new BankAccount(4L, null, 0, null), 1000), -2L);
        assertEquals(operationService.transfer(new BankAccount(1L, null, 0, null),
                new BankAccount(2L, null, 0, null), 1000), -2L);
        assertEquals(operationService.transfer(new BankAccount(1L, null, 0, "0123"),
                new BankAccount(2L, null, 0, null), 1000), -1L);
        assertEquals(operationService.transfer(new BankAccount(1L, null, 0, "0123"),
                new BankAccount(2L, null, 0, null), 12), 12);
    }

    @Test
    void test5GetAccounts(){
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(new BankAccount(1L, "Ivan", 99, "0123"));
        bankAccounts.add(new BankAccount(2L, "Vasya", 12, "4321"));
        assertEquals(accountService.getAccounts(), bankAccounts);
    }

}
