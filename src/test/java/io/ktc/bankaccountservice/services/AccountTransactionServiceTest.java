package io.ktc.bankaccountservice.services;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.enums.AccountTransactionType;
import io.ktc.bankaccountservice.generated.model.DepositRequest;
import io.ktc.bankaccountservice.repositories.AccountRepository;
import io.ktc.bankaccountservice.repositories.AccountTransactionRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountTransactionRepository accountTransactionRepository;

    @InjectMocks
    private AccountTransactionService accountTransactionService;

    @DisplayName("should deposit into account")
    @Test
    public void shouldDepositIntoAccount() {
        // GIVEN
        Account account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(5))
                .transactions(new ArrayList<>())
                .build();

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(10000));
        depositRequest.setDate(LocalDate.of(2024, 3, 8));

        AccountTransaction depositTransaction = AccountTransaction.builder()
                .date(LocalDate.of(2024, 3, 8))
                .accountTransactionType(AccountTransactionType.DEPOSIT)
                .amount(BigDecimal.valueOf(10000))
                .build();

        Mockito.when(this.accountTransactionRepository.save(depositTransaction))
                .thenReturn(depositTransaction);
        Mockito.when(this.accountRepository.save(account))
                .thenReturn(account);

        // WHEN
        AccountTransaction result = accountTransactionService.deposit(account, depositRequest);

        // THEN
        AssertionsForClassTypes.assertThat(depositTransaction).usingRecursiveComparison().isEqualTo(result);
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(10005));
    }

    @DisplayName("should not deposit into account when amount is negative")
    @Test
    public void shoulNotDepositIntoAccountWhenAmountIsNegative() {
        // GIVEN
        Account account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(5))
                .transactions(new ArrayList<>())
                .build();

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(-10000));
        depositRequest.setDate(LocalDate.of(2024, 3, 8));

        // WHEN
        // THEN
        assertThrows(IllegalArgumentException.class, () -> accountTransactionService.deposit(account, depositRequest));
    }
}
