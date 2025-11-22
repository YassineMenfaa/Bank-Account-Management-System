package com.bankapp.infrastructure.sql;

import com.bankapp.domain.model.BankAccount;
import com.bankapp.domain.model.CurrencyCode;
import com.bankapp.domain.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepositoryJpa accountRepository;

    public TransactionServiceImpl(AccountRepositoryJpa accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void localTransfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        BankAccount from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Source account not found"));

        BankAccount to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Destination account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);
    }

    @Override
    @Transactional
    public void forexDeal(UUID fromAccountId, UUID toAccountId, BigDecimal amount, CurrencyCode targetCurrency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        BankAccount from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Source account not found"));

        BankAccount to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Destination account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }

        // ⚠️ Conversion fictive — à remplacer par FxRateService plus tard
        BigDecimal conversionRate = BigDecimal.valueOf(10); // Exemple : 1 EUR = 10 MAD
        BigDecimal convertedAmount = amount.multiply(conversionRate);

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(convertedAmount));

        accountRepository.save(from);
        accountRepository.save(to);
    }
}
