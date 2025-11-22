package com.bankapp.domain.repository;

import com.bankapp.domain.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    // Tu peux ajouter des méthodes personnalisées si besoin, par exemple :
    // Optional<BankAccount> findByAccountNumber(String accountNumber);
    List<BankAccount> findByOwnerId(UUID ownerId);
}
