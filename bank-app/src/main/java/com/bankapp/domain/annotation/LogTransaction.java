package com.bankapp.domain.annotation;

import com.bankapp.domain.model.TransactionType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogTransaction {
    TransactionType value();
}
