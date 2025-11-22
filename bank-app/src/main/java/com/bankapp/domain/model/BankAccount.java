package com.bankapp.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    private UUID id;

    private String iban;

    @Enumerated(EnumType.STRING)
    private CurrencyCode currency;

    private BigDecimal balance;

    // relation avec User (simplifiée)
    @ManyToOne
    @JoinColumn(name = "user_id") // facultatif, sinon Hibernate crée une colonne automatiquement
    private User owner;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
