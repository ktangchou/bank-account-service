package io.ktc.bankaccountservice.services;

import io.ktc.bankaccountservice.entities.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAccounts();
    Optional<Account> findAccountById(Long id);
}
