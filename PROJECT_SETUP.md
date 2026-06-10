# E-Banking System Setup

## Machine

OS: Ubuntu 24.04

Java: OpenJDK 21.0.11

Maven: 3.8.7

Git: 2.54.0

MySQL: 8.0.46

Docker: 29.1.3

---

## Project Location

/home/jainamjain/Projects/ebanking-system

---

## Database

Database Name:
ebanking_system

---

## Run Application

```bash
cd ~/Projects/ebanking-system
./mvnw spring-boot:run
```

---

## Build Application

```bash
cd ~/Projects/ebanking-system
./mvnw clean install
```

---

## Package Structure

com.ebanking

├── auth
├── account
├── beneficiary
├── transfer
├── transaction
├── ledger
├── audit
├── notification
├── security
├── config
└── shared

---

## Architecture Principles

1. Spring Boot 3.x
2. Java 21
3. MySQL
4. JPA/Hibernate
5. JWT Authentication
6. Double Entry Ledger
7. Layered Architecture
8. Audit Logging
9. REST APIs
10. Docker Deployment

---

## Development Order

Phase 1

* BaseEntity
* Role
* UserStatus
* User

Phase 2

* Account
* AccountType
* AccountStatus

Phase 3

* Beneficiary

Phase 4

* LedgerEntry

Phase 5

* Transaction

Phase 6

* Transfer Module

Phase 7

* JWT Authentication

Phase 8

* Audit Logging

Phase 9

* Notifications

Phase 10

* Reporting
