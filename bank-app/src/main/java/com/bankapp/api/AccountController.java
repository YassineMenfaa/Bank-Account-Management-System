package com.bankapp.api;

import com.bankapp.domain.model.BankAccount;
import com.bankapp.domain.model.TransactionDocument;
import com.bankapp.domain.model.User;
import com.bankapp.domain.repository.BankAccountRepository;
import com.bankapp.infrastructure.sql.AccountRepositoryJpa;
import com.bankapp.domain.service.PdfService;
import com.bankapp.infrastructure.sql.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepositoryJpa accountRepository;

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public AccountController(AccountRepositoryJpa accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ✅ Créer un compte
    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount account) {
        BankAccount saved = accountRepository.save(account);
        return ResponseEntity.ok(saved);
    }

    // ✅ Récupérer un compte par ID
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable UUID id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Lister tous les comptes
//    @GetMapping("/all")
//    public List<BankAccount> getAllAccounts() {
//        return accountRepository.findAll();
//    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<BankAccount> accounts = accountRepository.findAll();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            e.printStackTrace(); // log dans la console
            return ResponseEntity.status(500).body("Erreur interne : " + e.getMessage());
        }
    }

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{id}/statement/pdf")
    public ResponseEntity<byte[]> getAccountStatement(@PathVariable UUID id) {
        // ✅ Récupérer l’utilisateur
        User user = userRepositoryJpa.findById(id).orElseThrow();

        // ✅ Récupérer ses comptes bancaires
        List<BankAccount> accounts = bankAccountRepository.findByOwnerId(id);

        // ✅ Générer le PDF
        byte[] pdf = pdfService.generateAccountStatement(user, accounts);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=statement.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostConstruct
    public void init() {
        System.out.println("✅ AccountController loaded");
    }
}
