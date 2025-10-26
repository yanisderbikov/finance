package org.example.finance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.finance.dto.TransactionRequest;
import org.example.finance.dto.TransactionResponse;
import org.example.finance.model.Account;
import org.example.finance.model.Transaction;
import org.example.finance.repository.AccountRepository;
import org.example.finance.repository.TransactionRepository;
import org.example.finance.service.*;
import org.example.finance.service.BalanceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionServiceInterface {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CurrencyConversionServiceInterface currencyConversionService;
    private final BalanceCalculator balanceCalculator;
    
    @Transactional
    @Override
    public TransactionResponse createTransaction(String accountName, TransactionRequest request) {
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("request amount could not be less than zero");
        }
        Account account = accountRepository.findByName(accountName)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with name: " + accountName));
        BigDecimal amountInUsd = currencyConversionService.convertToUsd(request.getAmount(), request.getCurrency());
        return switch (request.getType()) {
            case DEPOSIT -> deposit(account, request, amountInUsd);
            case WITHDRAW -> withdraw(account, request, amountInUsd);
            default -> throw new IllegalArgumentException("Not implemented");
        };
    }

    private TransactionResponse withdraw(Account account, TransactionRequest request, BigDecimal amountInUsd) {
        var currentBalance = balanceCalculator.getBalance(account);
        if (amountInUsd.compareTo(currentBalance) > 0) {
            throw new IllegalArgumentException("not enough money");
        }
        return createAndSaveTransaction(account, request, amountInUsd);
    }

    private TransactionResponse deposit(Account account, TransactionRequest request, BigDecimal amountInUsd) {
        // todo other checks if needed
        return createAndSaveTransaction(account, request, amountInUsd);
    }

    private TransactionResponse createAndSaveTransaction(Account account, TransactionRequest request, BigDecimal amountInUsd) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                request.getType(),
                request.getAmount(),
                request.getCurrency(),
                Instant.now(),
                amountInUsd,
                account
        );
        Transaction savedTransaction = transactionRepository.save(transaction);

        return new TransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getCurrency(),
                savedTransaction.getAmountInUsd(),
                savedTransaction.getTimestamp()
        );
    }
    
    @Override
    public List<TransactionResponse> getTransactionsByAccountName(String accountName) {
        List<Transaction> transactions = transactionRepository.findByAccountNameOrderByTimestampDesc(accountName);
        return transactions.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    private TransactionResponse mapToResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getAmountInUsd(),
                transaction.getTimestamp()
        );
    }
}
