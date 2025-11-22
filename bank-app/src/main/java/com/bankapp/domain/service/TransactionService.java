package com.bankapp.domain.service;

import com.bankapp.domain.model.CurrencyCode;
import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionService {

    void localTransfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);

    void forexDeal(UUID fromAccountId, UUID toAccountId, BigDecimal amount, CurrencyCode targetCurrency);
}