package org.example.finance.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.finance.model.Currency;
import org.example.finance.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private UUID id;
    private TransactionType type;
    private BigDecimal amount;
    private Currency currency;
    private BigDecimal amountInUsd;
    private Instant timestamp;
}


