markdown
# CoreBank-Java-Project

## Online Banking / Core Banking Simulation System

A Java-based banking simulation system developed for **CSE2006 – Programming in Java**. The project demonstrates Object-Oriented Programming, JDBC, exception handling, multithreading, transaction management, SHA-256 password hashing, and input validation.

---

## Overview

CoreBank-Java-Project simulates a real-world banking system with two user roles:

- **Customer** — manage accounts, deposit, withdraw, transfer funds, view history, apply savings interest
- **Administrator** — view all accounts, freeze/unfreeze accounts, generate reports and statistics

The system uses a layered architecture (UI → Service → DAO → MySQL) with JDBC, and enforces ACID-compliant fund transfers using database transactions and row-level locking.

---

## Features

### Customer Module
- Authentication and account ownership validation
- Balance enquiry
- Deposit, withdrawal, and fund transfer
- Transaction history
- Savings account interest calculation

### Admin Module
- Authentication
- View all accounts
- Freeze / unfreeze accounts
- Account reports and active/frozen statistics

### Security
- SHA-256 password hashing (no plain-text storage)
- Role-based access control
- Account ownership validation
- Input validation and custom exception handling

### Concurrency
- Concurrent fund transfers using Java threads
- Database transactions with `setAutoCommit(false)`, `commit()`, `rollback()`
- Row-level locking with `SELECT ... FOR UPDATE`
- Deterministic account-locking order to prevent deadlocks

---

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| MySQL | Database |
| JDBC (MySQL Connector/J) | Java–MySQL connectivity |
| Java Threads | Concurrent transactions |
| SHA-256 | Password hashing |
| BigDecimal | Financial arithmetic |
| Windows CMD | Compilation and execution |
| Git / GitHub | Version control |

---

## Project Structure
CoreBank-Java-Project
├── README.md
├── statement.md
├── lib/ # MySQL Connector/J driver
├── src/com/corebank/
│ ├── model/ # User, Customer, Account, Transaction
│ ├── dao/ # Database access layer
│ ├── service/ # Business logic
│ ├── exception/ # Custom banking exceptions
│ ├── util/ # DBConnection, PasswordUtil, InputUtil
│ ├── concurrent/ # TransferTask, ConcurrencyTest
│ └── ui/ # Main, CustomerMenu, AdminMenu
├── sql/schema.sql # Database schema
├── docs/ # Design documents
└── screenshots/ # Application screenshots

text

---

## Setup and Installation

### Prerequisites
- JDK 17 or later
- MySQL Server 8.x
- MySQL Connector/J (`lib/mysql-connector-j-26.7.0.jar`)
- Windows CMD

### Database Setup
1. Start MySQL Server.
2. Open MySQL Workbench.
3. Run the schema script:

```sql
SOURCE D:/CoreBank-Java-Project/sql/schema.sql;
This creates the corebank database with four tables: users, customers, accounts, transactions.

Compile
cmd
cd /d D:\CoreBank-Java-Project

javac -cp "lib\mysql-connector-j-26.7.0.jar" -d out ^
  src\com\corebank\util\*.java ^
  src\com\corebank\model\*.java ^
  src\com\corebank\exception\*.java ^
  src\com\corebank\dao\*.java ^
  src\com\corebank\service\*.java ^
  src\com\corebank\concurrent\*.java ^
  src\com\corebank\ui\*.java
Run
cmd
java -cp "out;lib\mysql-connector-j-26.7.0.jar" com.corebank.ui.Main
Test Accounts
Role	Username	Password
Customer	alok	alok123
Administrator	admin	admin123
Demo savings accounts: 1001001, 1001002

Testing
The system was validated through 14 test cases covering:

Customer and admin login (valid and invalid credentials)

Balance enquiry, deposit, withdrawal

Fund transfer between active accounts

Insufficient balance handling

Frozen account handling

Invalid amount handling

Transaction history retrieval

Admin freeze / unfreeze operations

Concurrent transfers of 500, 700, and 300 simultaneously

Database rollback on simulated failure

Concurrency Test
cmd
java -cp "out;lib\mysql-connector-j-26.7.0.jar" com.corebank.concurrent.ConcurrencyTest
This launches multiple TransferTask threads against the same pair of accounts and verifies the ledger remains consistent (no lost updates, no deadlocks).

Architecture
text
User Interface  →  Service Layer  →  DAO Layer  →  MySQL Database
(Main, Menus)      (Business)        (SQL)         (corebank)
UI Layer — Main, CustomerMenu, AdminMenu

Service Layer — AuthService, AccountService, TransactionService, InterestService, AdminReportService

DAO Layer — UserDAO, CustomerDAO, AccountDAO, TransactionDAO

Model Layer — User, Customer, Account, Transaction

Utility Layer — DBConnection, PasswordUtil, InputUtil

Exception Layer — AccountNotFoundException, AccountFrozenException, InsufficientBalanceException, InvalidAmountException

Screenshots
See the screenshots/ folder for:

Customer and admin login

Customer menu and admin menu

Deposit / transfer / transaction history

Concurrency test output

Documentation
Detailed design artefacts, UML diagrams, ER diagram, and full test cases are provided in the project report (docs/Project_Report.docx) and in the docs/ folder.

Author
Alok Kumar — Registration No. 24BEC10141
Course: CSE2006 – Programming in Java (VITyarthi)