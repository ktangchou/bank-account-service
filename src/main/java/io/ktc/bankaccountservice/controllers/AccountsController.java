package io.ktc.bankaccountservice.controllers;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.exceptions.AccountNotFoundException;
import io.ktc.bankaccountservice.generated.api.AccountsApi;
import io.ktc.bankaccountservice.generated.model.*;


import io.ktc.bankaccountservice.mappers.AccountMapper;
import io.ktc.bankaccountservice.mappers.AccountTransactionMapper;
import io.ktc.bankaccountservice.services.AccountService;
import io.ktc.bankaccountservice.services.AccountTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountsController implements AccountsApi {

    private AccountService accountService;
    private AccountTransactionService accountTransactionService;
    private AccountMapper accountMapper;
    private AccountTransactionMapper accountTransactionMapper;

    @Override
    public ResponseEntity<List<BankAccount>> findBankAccounts() {
        List<BankAccount> accounts = this.accountService.getAccounts().stream()
                .map(accountMapper::fromEntity)
                .toList();
        return ResponseEntity.ok(accounts);
    }

    @Override
    public ResponseEntity<BankAccount> findAccountById(Long accountId) {
        Account account = this.accountService.findAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        return ResponseEntity.ok(accountMapper.fromEntity(account));
    }

    @Override
    public ResponseEntity<Transaction> deposit(Long accountId, DepositRequest depositRequest) {
        Account account = this.accountService.findAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        AccountTransaction depositTransaction = this.accountTransactionService
                .deposit(account, depositRequest);

        return new ResponseEntity<>(accountTransactionMapper.fromEntity(depositTransaction), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Transaction> withdrawal(Long accountId, WithdrawRequest withdrawRequest) {
        Account account = this.accountService.findAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        AccountTransaction withdrawTransaction = this.accountTransactionService
                .withdraw(account, withdrawRequest);

        return new ResponseEntity<>(accountTransactionMapper.fromEntity(withdrawTransaction), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GetTransactionsResponse> listTransactions(Long accountId) {
        Account account = this.accountService.findAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        return ResponseEntity.ok(new GetTransactionsResponse()
                .items(account.getTransactions().stream()
                        .map(accountTransactionMapper::fromEntity)
                        .toList())
        );
    }
}
