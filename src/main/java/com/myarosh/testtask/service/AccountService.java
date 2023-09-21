package com.myarosh.testtask.service;

import com.myarosh.testtask.model.BankAccount;
import com.myarosh.testtask.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public long addAccount(BankAccount bankAccount) {
        return bankAccountRepository.saveAndFlush(bankAccount).getId();
    }

    public List<BankAccount> getAccounts() {
        return bankAccountRepository.findAll();
    }
}
