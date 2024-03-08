package io.ktc.bankaccountservice.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.ktc.bankaccountservice.generated.model.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BankAccount
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-08T02:03:15.863528+01:00[Europe/Paris]")
public class BankAccount implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private BigDecimal balance;

  @Valid
  private List<@Valid Transaction> transactions = new ArrayList<>();

  public BankAccount() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BankAccount(Long id, BigDecimal balance, List<@Valid Transaction> transactions) {
    this.id = id;
    this.balance = balance;
    this.transactions = transactions;
  }

  public BankAccount id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * account id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "account id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BankAccount balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * account balance
   * @return balance
  */
  @NotNull @Valid 
  @Schema(name = "balance", description = "account balance", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("balance")
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BankAccount transactions(List<@Valid Transaction> transactions) {
    this.transactions = transactions;
    return this;
  }

  public BankAccount addTransactionsItem(Transaction transactionsItem) {
    if (this.transactions == null) {
      this.transactions = new ArrayList<>();
    }
    this.transactions.add(transactionsItem);
    return this;
  }

  /**
   * account transactions history
   * @return transactions
  */
  @NotNull @Valid 
  @Schema(name = "transactions", description = "account transactions history", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactions")
  public List<@Valid Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<@Valid Transaction> transactions) {
    this.transactions = transactions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccount bankAccount = (BankAccount) o;
    return Objects.equals(this.id, bankAccount.id) &&
        Objects.equals(this.balance, bankAccount.balance) &&
        Objects.equals(this.transactions, bankAccount.transactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, balance, transactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccount {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

