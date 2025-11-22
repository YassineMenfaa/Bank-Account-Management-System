package com.bankapp.infrastructure.sql;

import com.bankapp.domain.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<BankAccount, UUID>{

}
