package com.ebanking.account.controller;

import com.ebanking.account.domain.Account;
import com.ebanking.account.infrastructure.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(
            AccountRepository accountRepository
    ) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccount(
            @PathVariable Long id
    ) {
        return accountRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Account not found"
                        )
                );
    }
}
