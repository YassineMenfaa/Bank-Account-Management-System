package com.bankapp.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "transactions") // nom de la collection MongoDB
public class TransactionDocument {
    @Id
    private UUID id;

    private LocalDateTime timestamp;
    private TransactionType type;

    @Field("from_account_id")
    private UUID fromAccountId;

    @Field("to_account_id")
    private UUID toAccountId;

    private BigDecimal amount;

    @Field("source_currency")
    private CurrencyCode sourceCurrency;

    @Field("target_currency")
    private CurrencyCode targetCurrency;

    @Field("fx_rate")
    private BigDecimal fxRate;

    private TransactionStatus status;
    private String reason;

    // ✅ Constructeur vide obligatoire pour MongoDB
    public TransactionDocument() {}

    // Getters et setters...
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public UUID getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(UUID fromAccountId) { this.fromAccountId = fromAccountId; }

    public UUID getToAccountId() { return toAccountId; }
    public void setToAccountId(UUID toAccountId) { this.toAccountId = toAccountId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public CurrencyCode getSourceCurrency() { return sourceCurrency; }
    public void setSourceCurrency(CurrencyCode sourceCurrency) { this.sourceCurrency = sourceCurrency; }

    public CurrencyCode getTargetCurrency() { return targetCurrency; }
    public void setTargetCurrency(CurrencyCode targetCurrency) { this.targetCurrency = targetCurrency; }

    public BigDecimal getFxRate() { return fxRate; }
    public void setFxRate(BigDecimal fxRate) { this.fxRate = fxRate; }

    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
