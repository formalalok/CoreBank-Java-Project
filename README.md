```markdown
# CoreBank-Java-Project

## Online Banking / Core Banking Simulation System

A Java-based banking simulation system developed for **CSE2006 – Programming in Java**. The project demonstrates Object-Oriented Programming, JDBC database connectivity, exception handling, collections, multithreading, transaction management, password hashing, and input validation.

---

## 1. Project Overview

CoreBank-Java-Project simulates the basic operations of a real-world banking system.

The system provides separate interfaces for:

* Customers
* Administrators

Customers can manage their accounts, perform banking transactions, and view transaction history. Administrators can manage account status and generate account reports.

---

## 2. Main Features

### Customer Module

* Customer authentication
* Account ownership validation
* Balance checking
* Deposit
* Withdrawal
* Fund transfer
* Transaction history
* Savings account interest calculation and application
* Logout

### Admin Module

* Administrator authentication
* View all accounts
* Freeze accounts
* Unfreeze accounts
* View account reports
* View active and frozen account statistics

### Security

* SHA-256 password hashing
* Role-based authentication
* Account ownership validation
* Input validation
* Custom exception handling

### Concurrency

The system supports concurrent fund transfers using Java multithreading.

Database transactions use:

* `setAutoCommit(false)`
* `commit()`
* `rollback()`
* `SELECT ... FOR UPDATE`
* Deterministic account locking

These mechanisms help maintain correct account balances when multiple transactions execute simultaneously.

---

## 3. Technologies Used

| Technology        | Purpose                   |
| ----------------- | ------------------------- |
| Java              | Application development   |
| MySQL             | Database                  |
| JDBC              | Java-MySQL connectivity   |
| MySQL Connector/J | JDBC driver               |
| Java Threads      | Concurrent transactions   |
| SHA-256           | Password hashing          |
| CMD               | Compilation and execution |
| MySQL Workbench   | Database management       |

---

## 4. Java Concepts Demonstrated

The project demonstrates the following concepts from Programming in Java:

* Classes and Objects
* Encapsulation
* Inheritance through exception hierarchy
* Constructors
* Methods
* Packages
* Interfaces / layered design
* Collections
* Exception Handling
* Custom Exceptions
* JDBC
* Multithreading
* Synchronization through database locking
* BigDecimal for financial calculations
* Input validation
* String and numeric processing
* Role-based application flow

---

## 5. Project Architecture

The project follows a layered architecture:

```text
User Interface
|
v
Service Layer
|
v
DAO Layer
|
v
MySQL Database
```

### UI Layer

Handles user interaction.

```text
Main
CustomerMenu
AdminMenu
```

### Service Layer

Contains business logic.

```text
AuthService
AccountService
TransactionService
InterestService
AdminReportService
```

### DAO Layer

Handles database operations.

```text
UserDAO
CustomerDAO
AccountDAO
TransactionDAO
```

### Model Layer

Represents application data.

```text
User
Customer
Account
Transaction
```

### Utility Layer

Contains reusable utilities.

```text
DBConnection
PasswordUtil
InputUtil
```

### Exception Layer

Contains custom exceptions.

```text
AccountNotFoundException
AccountFrozenException
InsufficientBalanceException
InvalidAmountException
```

### Concurrent Layer

Handles concurrent transaction testing.

```text
TransferTask
ConcurrencyTest
```

---

## 6. Database

Database name:

```text
corebank
```

Tables:

```text
users
customers
accounts
transactions
```

### Users

Stores login credentials and user roles.

### Customers

Stores customer profile information.

### Accounts

Stores account number, type, balance, and account status.

### Transactions

Stores deposits, withdrawals, transfers, and interest transactions.

---

## 7. Account Types

The system supports:

```text
SAVINGS
CURRENT
```

Interest calculation is available only for:

```text
SAVINGS
```

Accounts can have the following statuses:

```text
ACTIVE
FROZEN
```

---

## 8. Transaction Types

The system supports:

```text
DEPOSIT
WITHDRAWAL
TRANSFER
INTEREST
```

Every successful transaction is recorded in the transaction history.

---

## 9. Concurrency Handling

Fund transfers are implemented using database transactions.

The system locks both accounts before modifying their balances.

The basic process is:

```text
Start Transaction
|
v
Lock Source Account
|
v
Lock Destination Account
|
v
Check Balance and Status
|
v
Update Both Balances
|
v
Save Transaction
|
v
Commit
```

If an error occurs:

```text
Rollback
```

This prevents incomplete transfers and helps maintain database consistency.

---

## 10. Password Security

Passwords are not stored as plain text.

The project uses the SHA-256 hashing algorithm.

```text
Password
|
v
SHA-256
|
v
64-character hexadecimal hash
|
v
Stored in MySQL
```

During login, the entered password is hashed and compared with the stored hash.

---

## 11. Custom Exception Handling

The project uses custom exceptions for common banking errors.

Examples:

```text
AccountNotFoundException
AccountFrozenException
InsufficientBalanceException
InvalidAmountException
```

This allows banking-related errors to be handled separately from normal program execution.

---

## 12. Project Structure

```text
CoreBank-Java-Project
│
├── README.md
│
├── lib
│ └── mysql-connector-j-26.7.0.jar
│
├── src
│ └── com
│ └── corebank
│ ├── model
│ ├── dao
│ ├── service
│ ├── exception
│ ├── util
│ ├── concurrent
│ └── ui
│
├── sql
│ └── schemas.sql
│
├── docs
│
├── screenshots
│
└── out
```

---

## 13. Compilation

Open Windows CMD and navigate to the project:

```cmd
cd /d D:\CoreBank-Java-Project
```

Compile the project:

```cmd
javac -cp "lib\mysql-connector-j-26.7.0.jar" -d out src\com\corebank\util\*.java src\com\corebank\model\*.java src\com\corebank\exception\*.java src\com\corebank\dao\*.java src\com\corebank\service\*.java src\com\corebank\concurrent\*.java src\com\corebank\ui\*.java
```

---

## 14. Running the Application

Run:

```cmd
java -cp "out;lib\mysql-connector-j-26.7.0.jar" com.corebank.ui.Main
```

---

## 15. Test Accounts

### Customer

```text
Username: alok
Password: alok123
Role: CUSTOMER
```

### Administrator

```text
Username: admin
Password: admin123
Role: ADMIN
```

---

## 16. Current Demo Accounts

The project database contains two savings accounts associated with the customer profile.

```text
1001001
1001002
```

The exact balances may change during transaction testing.

---

## 17. Testing

The project has been tested for:

* User authentication
* Customer login
* Admin login
* Balance checking
* Fund transfers
* Transaction history
* Account freezing
* Account unfreezing
* Interest calculation
* Concurrent transfers
* Invalid input handling
* Insufficient balance handling
* Frozen account handling
* Database rollback

---

## 18. Concurrency Test

The project includes a dedicated concurrency test:

```text
ConcurrencyTest
```

It creates multiple transfer threads simultaneously.

Example concurrent transfers:

```text
500 → Account 1001001 to 1001002
700 → Account 1001001 to 1001002
300 → Account 1001001 to 1001002
```

The transaction service uses database locking and transactions to prevent inconsistent balance updates.

---

## 19. Database Consistency

Fund transfers follow the ACID transaction approach.

```text
BEGIN
|
+-- Lock accounts
|
+-- Validate transaction
|
+-- Update source balance
|
+-- Update destination balance
|
+-- Record transaction
|
+-- COMMIT
```

If any operation fails:

```text
ROLLBACK
```

---

## 20. Project Objective

The main objective of this project is to demonstrate how Java programming concepts can be combined to build a practical banking application.

The project combines:

```text
Java OOP
+
JDBC
+
MySQL
+
Exception Handling
+
Multithreading
+
Database Transactions
+
Password Hashing
```

---

## 21. Conclusion

CoreBank-Java-Project demonstrates the implementation of a practical banking simulation using Java and MySQL.

The project focuses on modular software design, secure authentication, database connectivity, transaction management, exception handling, and concurrent fund transfers.

It provides a practical application of concepts covered in **CSE2006 – Programming in Java**.
```

**How to use:**
1. Copy everything between the triple backticks above (the entire Markdown content).
2. Paste it into Notepad.
3. Save the file as `README.md` (make sure the file type is "All Files", not .txt).
4. Place it in the root of your GitHub repository.