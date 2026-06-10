package com.ebanking.transaction.controller;

import com.ebanking.account.domain.Account;
import com.ebanking.account.infrastructure.AccountRepository;
import com.ebanking.transaction.domain.Transaction;
import com.ebanking.transaction.infrastructure.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionController(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/transfer")
    public Transaction transfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount
    ) {

        if (fromAccountId.equals(toAccountId)) {
            throw new RuntimeException("Cannot transfer to same account");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow();

        Account to = accountRepository.findById(toAccountId)
                .orElseThrow();

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(
                from.getBalance().subtract(amount)
        );

        to.setBalance(
                to.getBalance().add(amount)
        );

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction tx = new Transaction();

        tx.setFromAccount(from);
        tx.setToAccount(to);
        tx.setAmount(amount);
        tx.setTransactionType("TRANSFER");

        return transactionRepository.save(tx);
    }

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getAccountTransactions(
            @PathVariable Long accountId
    ) {
        return transactionRepository
                .findByFromAccountIdOrToAccountId(
                        accountId,
                        accountId
                );
    }

    @GetMapping("/account/{accountId}/mini-statement")
    public List<Transaction> miniStatement(
            @PathVariable Long accountId
    ) {
        return transactionRepository
                .findByFromAccountIdOrToAccountId(
                        accountId,
                        accountId
                );
    }
}
