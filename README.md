# Finance Account Balance API

A REST API for managing account balances with multi-currency support. This application allows you to create accounts, perform deposits and withdrawals in different currencies (USD, EUR, BYN, RUB), and track transaction history.

Public postman api: https://www.postman.com/aviation-geoscientist-36550161/finance-test-task
for use insert local vars 

## Features

- **Account Management**: Create accounts with unique names
- **Multi-Currency Support**: Support for USD, EUR, BYN, and RUB currencies
- **Transaction History**: Track all deposits and withdrawals
- **Currency Conversion**: Automatic conversion to USD for balance calculation
- **RESTful API**: Clean REST endpoints for all operations


## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone the repository** (if not already done):
   ```bash
   git clone <repository-url>
   cd finance
   ```

2. **Run and build the application**:
   ```bash
   docker compose up --build
   ```

3. **Access the application**:
   - API Base URL: `http://localhost:8080`
   - H2 Database Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (leave empty)

Public postman api: https://www.postman.com/aviation-geoscientist-36550161/finance-test-task


# API Documentation

### Base URL
```
http://localhost:8080/api
```

## Account Endpoints

### Create Account
**Request:**
```http
POST /api/accounts
Content-Type: application/json

{
  "name": "my-account"
}
```

**Response:**
```json
{
  "name": "test-name-yan",
  "balance": 0
}
```

### Get Account by Name
**Request:**
```http
GET /api/accounts/name/{accountName}
```
**Response:**
```json
{
  "name": "test-name-yan",
  "balance": 0
}
```

**Request:**
#### Get All Accounts
```http
GET /api/accounts
```
**Response:**
```json
[
    {
        "name": "test-name-yan",
        "balance": 0
    },
    {
        "name": "test-second-name-kate",
        "balance": 20.84
    }
]
```

## Transaction Endpoints

#### Create Transaction (by Account ID)
```http
POST /api/transactions/account/{accountId}
Content-Type: application/json

{
  "type": "DEPOSIT",
  "amount": 100.00,
  "currency": "USD"
}
```

#### Create Transaction (by Account Name)
```http
POST /api/transactions/account/name/{accountName}
Content-Type: application/json

{
  "type": "WITHDRAW",
  "amount": 50.00,
  "currency": "EUR"
}
```

**Response:**
```json
{
  "transactionId": "uuid-string",
  "type": "DEPOSIT",
  "amount": 100.00,
  "currency": "USD",
  "amountInUsd": 100.00,
  "timestamp": "2024-01-01T12:00:00"
}
```
## Use APIs

for use api please use my public api on postman
Public postman api: https://www.postman.com/aviation-geoscientist-36550161/finance-test-task
for use insert local vars



## Supported Currencies

| Currency | Code | Exchange Rate to USD |
|----------|------|---------------------|
| US Dollar | USD | 1.00 |
| Euro | EUR | 1.08 |
| Belarusian Ruble | BYN | 0.31 |
| Russian Ruble | RUB | 0.011 |


### Adding New Features

1. **New Currency**: Update `Currency` enum and `CurrencyConversionService`
2. **New Transaction Type**: Update `TransactionType` enum
3. **New Endpoints**: Add methods to controllers and corresponding service methods

## License

This project is licensed under the MIT License.



