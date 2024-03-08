package io.ktc.bankaccountservice;

import io.ktc.bankaccountservice.entities.Account;
import io.ktc.bankaccountservice.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
@Profile("!test")
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(Account.builder()
                            .balance(BigDecimal.valueOf(1500))
                            .build()
            );
            accountRepository.save(Account.builder()
                    .balance(BigDecimal.valueOf(2500))
                    .build()
            );
        };
    }
}
