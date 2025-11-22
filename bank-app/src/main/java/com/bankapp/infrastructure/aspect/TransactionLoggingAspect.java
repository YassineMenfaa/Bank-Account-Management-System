package com.bankapp.infrastructure.aspect;

import com.bankapp.domain.annotation.LogTransaction;
import com.bankapp.domain.model.TransactionDocument;
import com.bankapp.domain.model.TransactionStatus;
import com.bankapp.domain.port.TransactionLogPort;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class TransactionLoggingAspect {

    private final TransactionLogPort transactionLogPort;

    public TransactionLoggingAspect(TransactionLogPort transactionLogPort) {
        this.transactionLogPort = transactionLogPort;
    }

    @Around("@annotation(logTx)")
    public Object logTransaction(ProceedingJoinPoint pjp, LogTransaction logTx) throws Throwable {
        TransactionDocument doc = new TransactionDocument();
        doc.setId(UUID.randomUUID());
        doc.setTimestamp(LocalDateTime.now());
        doc.setType(logTx.value());

        try {
            Object result = pjp.proceed();
            doc.setStatus(TransactionStatus.SUCCESS);
            transactionLogPort.log(doc);
            return result;
        } catch (Exception ex) {
            doc.setStatus(TransactionStatus.FAILED);
            doc.setReason(ex.getMessage());
            transactionLogPort.log(doc);
            throw ex;
        }
    }
}