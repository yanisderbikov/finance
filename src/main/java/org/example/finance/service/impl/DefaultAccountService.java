package org.example.finance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.finance.dto.AccountResponse;
import org.example.finance.dto.CreateAccountRequest;
import org.example.finance.model.Account;
import org.example.finance.repository.AccountRepository;
import org.example.finance.service.AccountServiceInterface;
import org.example.finance.service.BalanceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultAccountService implements AccountServiceInterface {
    
    private final AccountRepository accountRepository;
    private final BalanceCalculator balanceCalculator;

    @Transactional
    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        if (accountRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Account with name '" + request.getName() + "' already exists");
        }
        
        Account account = new Account(request.getName());
        Account savedAccount = accountRepository.save(account);
        
        return new AccountResponse(savedAccount.getName(), BigDecimal.ZERO);
    }

    
    @Override
    public AccountResponse getAccountByName(String name) {
        Account account = accountRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with name: " + name));
        
        return new AccountResponse(account.getName(), balanceCalculator.getBalance(account));
    }
    
    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(account -> new AccountResponse(account.getName(), balanceCalculator.getBalance(account)))
                .collect(Collectors.toList());
    }
}

