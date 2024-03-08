package io.ktc.bankaccountservice.services;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.generated.model.DepositRequest;
import io.ktc.bankaccountservice.generated.model.WithdrawRequest;

public interface AccountTransactionService {

    AccountTransaction deposit(Account account, DepositRequest depositRequest);
    AccountTransaction withdraw(Account account, WithdrawRequest withdrawRequest);
}
