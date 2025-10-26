package org.example.finance.service;

import org.example.finance.model.Account;

import java.math.BigDecimal;

/**
 * Интерфейс для расчета баланса счета
 */
public interface BalanceCalculator {
    
    /**
     * Рассчитывает текущий баланс счета
     * 
     * @param account счет
     * @return текущий баланс в USD
     */
    BigDecimal getBalance(Account account);
}

