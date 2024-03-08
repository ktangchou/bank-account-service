package io.ktc.bankaccountservice.mappers;

import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.enums.AccountTransactionType;
import io.ktc.bankaccountservice.generated.model.Transaction;
import io.ktc.bankaccountservice.generated.model.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class AccountTransactionMapper {
    public Transaction fromEntity(AccountTransaction accountTransaction) {
        Transaction transaction = new Transaction();
        transaction.setId(accountTransaction.getId());
        transaction.setAmount(accountTransaction.getAmount());
        transaction.setTransactionType(fromEntity(accountTransaction.getAccountTransactionType()));
        transaction.setDate(accountTransaction.getDate());
        return transaction;
    }

    public TransactionType fromEntity(AccountTransactionType transactionType) {
        return switch (transactionType) {
            case DEPOSIT:
                yield TransactionType.DEPOSIT;
            case WITHDRAWAL:
                yield TransactionType.WITHDRAWAL;
        };
    }
}

