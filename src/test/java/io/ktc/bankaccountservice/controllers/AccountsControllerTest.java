package io.ktc.bankaccountservice.controllers;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.entities.AccountTransaction;
import io.ktc.bankaccountservice.enums.AccountTransactionType;
import io.ktc.bankaccountservice.generated.model.Transaction;
import io.ktc.bankaccountservice.generated.model.TransactionType;
import io.ktc.bankaccountservice.mappers.AccountMapper;
import io.ktc.bankaccountservice.mappers.AccountTransactionMapper;
import io.ktc.bankaccountservice.services.AccountService;
import io.ktc.bankaccountservice.services.AccountTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = AccountsController.class)
@Import(AccountsController.class)
@ExtendWith(MockitoExtension.class)
public class AccountsControllerTest {
    @MockBean
    private AccountService accountService;
    @MockBean
    private AccountTransactionService accountTransactionService;
    @MockBean
    private AccountMapper accountMapper;
    @MockBean
    private AccountTransactionMapper accountTransactionMapper;

    @Autowired
    private MockMvc mockMvc;

    private Account account;

    private List<Transaction> transactions;

    @BeforeEach()
    public void setUp() {
        List<AccountTransaction> accountTransactions = List.of(
                new AccountTransaction(1L, LocalDate.of(2024, 3, 8), BigDecimal.TEN, AccountTransactionType.DEPOSIT),
                new AccountTransaction(2L, LocalDate.of(2024, 3, 8), BigDecimal.ONE, AccountTransactionType.WITHDRAWAL)
        );
        this.account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(10000))
                .transactions(accountTransactions)
                .build();

        this.transactions = List.of(
                new Transaction(1L, LocalDate.of(2024, 3, 8), BigDecimal.TEN, TransactionType.DEPOSIT),
                new Transaction(2L, LocalDate.of(2024, 3, 8), BigDecimal.ONE, TransactionType.WITHDRAWAL)
        );
    }

    @Test
    public void shouldGetAllTransactions() throws Exception {
        // GIVEN
        Long accountId = 1L;
        when(this.accountService.findAccountById(any())).thenReturn(Optional.of(account));
        when(this.accountTransactionMapper.fromEntity((account.getTransactions().get(0)))).thenReturn(transactions.get(0));
        when(this.accountTransactionMapper.fromEntity((account.getTransactions().get(1)))).thenReturn(transactions.get(1));

        // WHEN
        // THEN
        mockMvc.perform(get("/accounts/{accountId}/transactions", accountId))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                    {\
                    "items":[\
                    {"id":1,"date":"2024-03-08","amount":10,"transactionType":"DEPOSIT"},\
                    {"id":2,"date":"2024-03-08","amount":1,"transactionType":"WITHDRAWAL"}\
                    ]\
                    }\
                    """));
    }
}
