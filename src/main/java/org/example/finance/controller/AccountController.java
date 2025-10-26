package org.example.finance.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finance.dto.AccountResponse;
import org.example.finance.dto.CreateAccountRequest;
import org.example.finance.service.AccountServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountServiceInterface accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<AccountResponse> getAccountByName(@PathVariable String name) {
        AccountResponse response = accountService.getAccountByName(name);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> responses = accountService.getAllAccounts();
        return ResponseEntity.ok(responses);
    }
}

