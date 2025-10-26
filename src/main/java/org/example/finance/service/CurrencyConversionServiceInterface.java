package org.example.finance.service;

import org.example.finance.model.Currency;

import java.math.BigDecimal;

/**
 * Интерфейс для конвертации валют
 */
public interface CurrencyConversionServiceInterface {
    
    /**
     * Конвертирует сумму в USD
     * 
     * @param amount сумма для конвертации
     * @param fromCurrency исходная валюта
     * @return сумма в USD
     */
    BigDecimal convertToUsd(BigDecimal amount, Currency fromCurrency);
    
    /**
     * Получает курс обмена валюты к USD
     * 
     * @param currency валюта
     * @return курс обмена
     */
    BigDecimal getExchangeRate(Currency currency);
}


