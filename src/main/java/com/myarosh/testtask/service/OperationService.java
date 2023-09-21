package com.myarosh.testtask.service;

import com.myarosh.testtask.model.BankAccount;
import com.myarosh.testtask.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Transactional
    public long deposit(BankAccount bankAccount, long money) {
        try {
            bankAccount = bankAccountRepository.getReferenceById(bankAccount.getId());
            bankAccount.setBalance(bankAccount.getBalance() + money);
        } catch (Exception e){
            return -3L;
        }
        return bankAccountRepository.saveAndFlush(bankAccount).getBalance();
    }

    @Transactional
    public long withdraw(BankAccount bankAccount, long money) {
        String pin = bankAccount.getPin();
        try {
            bankAccount = bankAccountRepository.getReferenceById(bankAccount.getId());
            if (!bankAccount.getPin().equals(pin)) return -2L;
            if (bankAccount.getBalance() - money < 0) return -1L;
            bankAccount.setBalance(bankAccount.getBalance() - money);
        } catch (Exception e){
            return -3L;
        }
        return bankAccountRepository.saveAndFlush(bankAccount).getBalance();

    }

    @Transactional
    public long transfer(BankAccount fromAccount, BankAccount toAccount, long money) {
        String pin = fromAccount.getPin();
        try {
            fromAccount = bankAccountRepository.getReferenceById(fromAccount.getId());
            toAccount = bankAccountRepository.getReferenceById(toAccount.getId());
            if (!fromAccount.getPin().equals(pin)) return -2L;
            if (fromAccount.getBalance() - money < 0) return -1L;
            fromAccount.setBalance(fromAccount.getBalance() - money);
            toAccount.setBalance(toAccount.getBalance() + money);
        } catch (Exception e){
            return -3L;
        }
        return money;
    }
}
