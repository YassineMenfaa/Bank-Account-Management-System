# 💳 BankApp – Banking System Project

A robust Spring Boot application for managing users, bank accounts, and transactions, with PDF export functionality for account statements.

---

## 📌 Features

- ✅ User management (CRUD)
- ✅ Bank account creation and linking to users
- ✅ Transaction logging (local and forex transfers)
- ✅ PDF export of account statements
- ✅ MongoDB logging via Aspect-Oriented Programming
- ✅ Clean JSON serialization using DTOs

---

## 🧱 Tech Stack

| Layer         | Technology                  |
|---------------|-----------------------------|
| Backend       | Java 17, Spring Boot        |
| Database      | PostgreSQL, MongoDB         |
| PDF Export    | OpenPDF / iText             |
| Container     | Docker, Docker Compose      |
| Build Tool    | Maven                       |
| Versioning    | Git, GitHub Desktop         |

---

## 📂 Project Structure

bank-app/ 
├── src/ 
│ └── main/ 
│ ├── java/com/bankapp/ 
│ │ ├── controller/AccountController.java 
│ │ ├── domain/model/User.java, BankAccount.java 
│ │ ├── domain/service/PdfService.java 
│ │ ├── application/dto/LocalTransferRequest.java 
│ │ ├── infrastructure/aspect/TransactionLoggingAspect.java 
│ │ └── infrastructure/mongo/TransactionLogAdapter.java 
│ └── resources/application.properties 
├── docker-compose.yml 
├── README.md 
├── .gitignore 
└── pom.xml


---

## 📄 API Endpoints

### 🔹 Export PDF Statement
```http
GET /accounts/{id}/statement/pdf

View PDF Inline (optional)
GET /accounts/{id}/statement/view

Sample Data (pgAdmin)
-- User
INSERT INTO users (id, name, email, phone, address, blocked)
VALUES ('11111111-1111-1111-1111-111111111111', 'Yassine Menfaa', 'yassine@example.com', '0612345678', 'Fès, Maroc', FALSE);

-- Bank Account
INSERT INTO bank_accounts (id, iban, currency, balance, owner_id, created_at)
VALUES ('22222222-2222-2222-2222-222222222222', 'MA123456789012345678901234', 'MAD', 10000.00, '11111111-1111-1111-1111-111111111111', CURRENT_DATE);

-- Transactions
INSERT INTO transactions (id, account_id, type, amount, currency, description, status, timestamp)
VALUES 
('33333333-3333-3333-3333-333333333333', '22222222-2222-2222-2222-222222222222', 'DEBIT', 500.00, 'MAD', 'Paiement facture électricité', 'SUCCESS', CURRENT_TIMESTAMP),
('44444444-4444-4444-4444-444444444444', '22222222-2222-2222-2222-222222222222', 'CREDIT', 8000.00, 'MAD', 'Salaire Novembre', 'SUCCESS', CURRENT_TIMESTAMP),
('55555555-5555-5555-5555-555555555555', '22222222-2222-2222-2222-222222222222', 'DEBIT', 1000.00, 'MAD', 'Retrait guichet automatique', 'SUCCESS', CURRENT_TIMESTAMP);
