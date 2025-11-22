package com.bankapp.infrastructure.mongo;

import com.bankapp.domain.model.TransactionDocument;
import com.bankapp.domain.port.TransactionLogPort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionLogAdapter implements TransactionLogPort {

    private final TransactionMongoRepository repository;

    public TransactionLogAdapter(TransactionMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void log(TransactionDocument transaction) {
        repository.save(transaction);
    }
}

interface TransactionMongoRepository extends MongoRepository<TransactionDocument, String> {}
