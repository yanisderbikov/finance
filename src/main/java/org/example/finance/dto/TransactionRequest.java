package org.example.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finance.model.Currency;
import org.example.finance.model.TransactionType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private TransactionType type;
    private BigDecimal amount;
    private Currency currency;
}

