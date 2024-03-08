package io.ktc.bankaccountservice.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type de transaction
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-08T02:03:15.863528+01:00[Europe/Paris]")
public enum TransactionType {
  
  WITHDRAWAL("WITHDRAWAL"),
  
  DEPOSIT("DEPOSIT");

  private String value;

  TransactionType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TransactionType fromValue(String value) {
    for (TransactionType b : TransactionType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

