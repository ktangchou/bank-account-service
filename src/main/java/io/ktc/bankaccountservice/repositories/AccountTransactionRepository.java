package io.ktc.bankaccountservice.repositories;

import io.ktc.bankaccountservice.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
}
