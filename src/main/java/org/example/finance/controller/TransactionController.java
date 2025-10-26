package org.example.finance.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finance.dto.TransactionRequest;
import org.example.finance.dto.TransactionResponse;
import org.example.finance.service.TransactionServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionServiceInterface transactionService;
    
    @PostMapping("/account/name/{accountName}")
    public ResponseEntity<TransactionResponse> createTransactionByName(
            @PathVariable String accountName,
            @Valid @RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.createTransaction(accountName, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/account/name/{accountName}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccountName(@PathVariable String accountName) {
        List<TransactionResponse> responses = transactionService.getTransactionsByAccountName(accountName);
        return ResponseEntity.ok(responses);
    }
}

