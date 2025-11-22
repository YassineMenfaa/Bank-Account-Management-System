package com.bankapp.controller;

import com.bankapp.application.dto.LocalTransferRequest;
import com.bankapp.application.dto.ForexTransferRequest;
import com.bankapp.domain.model.CurrencyCode;
import com.bankapp.domain.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ✅ Virement local
    @PostMapping("/local")
    public ResponseEntity<String> localTransfer(@RequestBody LocalTransferRequest request) {
        try {
            transactionService.localTransfer(
                    request.getFromAccountId(),
                    request.getToAccountId(),
                    request.getAmount()
            );
            return ResponseEntity.ok("Local transfer completed successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Virement avec conversion
    @PostMapping("/forex")
    public ResponseEntity<String> forexDeal(@RequestBody ForexTransferRequest request) {
        try {
            transactionService.forexDeal(
                    request.getFromAccountId(),
                    request.getToAccountId(),
                    request.getAmount(),
                    request.getTargetCurrency()
            );
            return ResponseEntity.ok("Forex deal completed successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}