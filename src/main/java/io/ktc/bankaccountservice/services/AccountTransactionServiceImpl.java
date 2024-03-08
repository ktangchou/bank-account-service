package io.ktc.bankaccountservice.services;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.enums.AccountTransactionType;
import io.ktc.bankaccountservice.generated.model.DepositRequest;
import io.ktc.bankaccountservice.generated.model.WithdrawRequest;
import io.ktc.bankaccountservice.repositories.AccountRepository;
import io.ktc.bankaccountservice.repositories.AccountTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {
    private AccountRepository accountRepository;
    private AccountTransactionRepository accountTransactionRepository;

    @Override
    @Transactional
    public AccountTransaction deposit(Account account, DepositRequest depositRequest) {
        if (depositRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount to deposit should not be negative " + depositRequest.getAmount());
        }

        AccountTransaction depositTransaction = AccountTransaction.builder()
                .date(depositRequest.getDate())
                .accountTransactionType(AccountTransactionType.DEPOSIT)
                .amount(depositRequest.getAmount())
                .build();

        this.accountTransactionRepository.save(depositTransaction);

        account.getTransactions().add(depositTransaction);
        account.setBalance(account.getBalance().add(depositRequest.getAmount()));

        this.accountRepository.save(account);

        return depositTransaction;
    }

    @Override
    @Transactional
    public AccountTransaction withdraw(Account account, WithdrawRequest withdrawRequest) {
        if (withdrawRequest.getAmount().compareTo(account.getBalance()) > 0) {
            throw new IllegalArgumentException("Amount to withdraw should not be higher than balance " + withdrawRequest.getAmount());
        }

        AccountTransaction withdrawTransaction = AccountTransaction.builder()
                .date(withdrawRequest.getDate())
                .accountTransactionType(AccountTransactionType.WITHDRAWAL)
                .amount(withdrawRequest.getAmount())
                .build();

        this.accountTransactionRepository.save(withdrawTransaction);

        account.getTransactions().add(withdrawTransaction);
        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));

        this.accountRepository.save(account);

        return withdrawTransaction;
    }
}
