package io.ktc.bankaccountservice.services;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAccounts() {
        return new ArrayList<>(this.accountRepository.findAll());
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return this.accountRepository.findById(id);
    }
}
