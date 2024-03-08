package io.ktc.bankaccountservice.mappers;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.generated.model.BankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountMapper {
    private AccountTransactionMapper accountTransactionMapper;

    public BankAccount fromEntity(Account account) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(account.getId());
        bankAccount.setBalance(account.getBalance());
        bankAccount.setTransactions(account.getTransactions().stream()
                .map(accountTransactionMapper::fromEntity)
                .toList());
        return bankAccount;
    }
}
