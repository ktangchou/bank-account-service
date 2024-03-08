package io.ktc.bankaccountservice.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DepositRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-08T02:03:15.863528+01:00[Europe/Paris]")
public class DepositRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  private BigDecimal amount;

  public DepositRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DepositRequest(LocalDate date, BigDecimal amount) {
    this.date = date;
    this.amount = amount;
  }

  public DepositRequest date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @NotNull @Valid 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public DepositRequest amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * deposit amount
   * @return amount
  */
  @NotNull @Valid 
  @Schema(name = "amount", description = "deposit amount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DepositRequest depositRequest = (DepositRequest) o;
    return Objects.equals(this.date, depositRequest.date) &&
        Objects.equals(this.amount, depositRequest.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DepositRequest {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

