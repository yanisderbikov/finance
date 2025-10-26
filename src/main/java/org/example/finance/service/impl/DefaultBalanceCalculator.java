package org.example.finance.service.impl;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.example.finance.model.Account;
import org.example.finance.model.TransactionType;
import org.example.finance.service.BalanceCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Реализация по умолчанию для расчета баланса счета
 */
@Component
public class DefaultBalanceCalculator implements BalanceCalculator {

    @Transactional
    @Override
    public BigDecimal getBalance(@NonNull Account account) {
        if (account.getTransactions() == null || account.getTransactions().isEmpty()) {
            return BigDecimal.ZERO;
        }

        var balance =  account.getTransactions().stream()
                .map(transaction -> {
                    BigDecimal amountInUsd = transaction.getAmountInUsd();

                    return switch (transaction.getType()) {
                        case DEPOSIT -> amountInUsd;
                        case WITHDRAW -> amountInUsd.negate();
                        default -> throw new IllegalArgumentException(
                                "Not implemented transaction type: " + transaction.getType()
                        );
                    };
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return balance.setScale(2, RoundingMode.HALF_UP);
    }
}
