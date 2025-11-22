package com.bankapp.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AccountStatement {
    private UUID id;
    private LocalDateTime generatedAt;
    private User user;
    private BankAccount account;
    private List<TransactionDocument> transactions;
    private String pdfPath;
}