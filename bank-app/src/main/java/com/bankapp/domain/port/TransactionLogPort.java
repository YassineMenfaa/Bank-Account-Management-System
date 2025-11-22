package com.bankapp.domain.port;

import com.bankapp.domain.model.TransactionDocument;

public interface TransactionLogPort {
    void log(TransactionDocument transaction);
}
