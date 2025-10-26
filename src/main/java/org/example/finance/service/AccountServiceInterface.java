package org.example.finance.service;

import org.example.finance.dto.AccountResponse;
import org.example.finance.dto.CreateAccountRequest;
import org.example.finance.model.Account;

import java.util.List;

/**
 * Интерфейс для работы со счетами
 */
public interface AccountServiceInterface {
    
    /**
     * Создает новый счет
     * 
     * @param request данные для создания счета
     * @return созданный счет
     */
    AccountResponse createAccount(CreateAccountRequest request);

    /**
     * Получает счет по имени
     * 
     * @param name имя счета
     * @return данные счета
     */
    AccountResponse getAccountByName(String name);
    
    /**
     * Получает все счета
     * 
     * @return список всех счетов
     */
    List<AccountResponse> getAllAccounts();
}


