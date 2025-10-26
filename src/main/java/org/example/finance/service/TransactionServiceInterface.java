package org.example.finance.service;

import org.example.finance.dto.TransactionRequest;
import org.example.finance.dto.TransactionResponse;

import java.util.List;

/**
 * Интерфейс для работы с транзакциями
 */
public interface TransactionServiceInterface {

    /**
     * Создает транзакцию для счета по имени
     * 
     * @param accountName имя счета
     * @param request данные транзакции
     * @return созданная транзакция
     */
    TransactionResponse createTransaction(String accountName, TransactionRequest request);
    
    /**
     * Получает транзакции по имени счета
     * 
     * @param accountName имя счета
     * @return список транзакций
     */
    List<TransactionResponse> getTransactionsByAccountName(String accountName);
}


