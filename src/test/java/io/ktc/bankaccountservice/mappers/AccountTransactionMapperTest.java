package io.ktc.bankaccountservice.mappers;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.enums.AccountTransactionType;
import io.ktc.bankaccountservice.generated.model.Transaction;
import io.ktc.bankaccountservice.generated.model.TransactionType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountTransactionMapperTest {

    AccountTransactionMapper accountTransactionMapper = new AccountTransactionMapper();

    @Test
    @DisplayName("should map account transaction to transaction")
    public void shouldMapAccountTransactionToTransaction() {
        // GIVEN
        AccountTransaction accountTransaction = AccountTransaction.builder()
                .id(12L)
                .accountTransactionType(AccountTransactionType.DEPOSIT)
                .date(LocalDate.of(2024, 3, 8))
                .amount(BigDecimal.valueOf(777))
                .build();

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setId(12L);
        expectedTransaction.setTransactionType(TransactionType.DEPOSIT);
        expectedTransaction.setDate(LocalDate.of(2024, 3, 8));
        expectedTransaction.setAmount(BigDecimal.valueOf(777));

        // WHEN
        Transaction result = accountTransactionMapper.fromEntity(accountTransaction);

        // THEN
        AssertionsForClassTypes.assertThat(expectedTransaction).usingRecursiveComparison().isEqualTo(result);
    }

}
