# CoreBank-Java-Project

## 1. Problem Statement

Banking systems need to securely manage customer accounts, balances, transactions, and transaction records while maintaining data consistency. The purpose of this project is to develop a Java-based **Online Banking / Core Banking Simulation System** that demonstrates how common banking operations can be implemented using Java programming concepts and database connectivity.

The system provides authentication, account management, deposits, withdrawals, fund transfers, transaction history, interest calculation, and administrative account management. It also demonstrates exception handling and concurrent transaction processing.

---

## 2. Scope of the Project

The project is a **command-line based banking simulation system** developed using Java, JDBC, and MySQL.

The scope includes:

* Customer and administrator authentication
* Savings and current account support
* Balance enquiry
* Deposit and withdrawal operations
* Fund transfers between accounts
* Transaction history
* Savings account interest calculation and application
* Account freeze and unfreeze operations
* Account reports for administrators
* Custom exception handling
* Concurrent fund transfer testing
* Database-based storage of users, customers, accounts, and transactions

The project focuses on demonstrating Java programming concepts in a practical banking application rather than providing a production-ready banking platform.

---

## 3. Target Users

### Customers

Customers can securely log in and perform basic banking operations such as:

* Checking account balance
* Depositing money
* Withdrawing money
* Transferring funds
* Viewing transaction history
* Applying savings account interest

### Administrators

Administrators can:

* View account information
* Freeze accounts
* Unfreeze accounts
* View account reports
* Monitor active and frozen account statistics

### Students / Learners

The project can also be used as a practical example for understanding Java concepts such as:

* Object-Oriented Programming
* JDBC
* Exception handling
* Custom exceptions
* Multithreading
* Database transactions
* Input validation

---

## 4. High-Level Features

* **User Authentication** – Separate customer and administrator login.
* **Account Management** – Supports savings/current accounts and account status management.
* **Deposit & Withdrawal** – Allows customers to add or withdraw funds with validation.
* **Fund Transfer** – Transfers money between accounts using database transactions.
* **Transaction History** – Records and displays banking transactions.
* **Interest Calculation** – Calculates and applies interest to savings accounts.
* **Admin Management** – Allows administrators to freeze/unfreeze accounts and view reports.
* **Exception Handling** – Uses custom exceptions for banking-related errors.
* **Concurrency Handling** – Uses Java multithreading and database locking for concurrent fund transfer testing.
* **MySQL Database** – Stores users, customers, accounts, and transaction records.
