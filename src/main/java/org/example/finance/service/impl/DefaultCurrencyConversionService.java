package org.example.finance.service.impl;

import org.example.finance.model.Currency;
import org.example.finance.service.CurrencyConversionServiceInterface;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class DefaultCurrencyConversionService implements CurrencyConversionServiceInterface {
    
    // Hardcoded exchange rates to USD
    private static final Map<Currency, BigDecimal> EXCHANGE_RATES = Map.of(
        Currency.USD, BigDecimal.ONE,
        Currency.EUR, new BigDecimal("1.08"),
        Currency.BYN, new BigDecimal("0.31"),
        Currency.RUB, new BigDecimal("0.011")
    );
    
    @Override
    public BigDecimal convertToUsd(BigDecimal amount, Currency fromCurrency) {
        if (fromCurrency == Currency.USD) {
            return amount;
        }
        
        BigDecimal rate = EXCHANGE_RATES.get(fromCurrency);
        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency: " + fromCurrency);
        }
        
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
    
    @Override
    public BigDecimal getExchangeRate(Currency currency) {
        return EXCHANGE_RATES.get(currency);
    }
}

