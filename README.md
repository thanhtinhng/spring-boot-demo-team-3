# Spring Boot Banking Demo

A simple banking demo application built with Spring Boot, Spring Data JPA, and H2 in-memory database.  
It demonstrates basic account management, money transfer, transaction logging, and RESTful API design.

## Features

- User and Account entities with sample data initialization
- Transfer money between accounts with transaction management and rollback simulation
- Transaction logging with separate transaction propagation
- REST API for account operations
- H2 in-memory database for easy setup and testing

## Project Structure

```
src/
  main/
    java/
      demo/
        DemoApplication.java
        controller/
        entity/
        repository/
        service/
    resources/
      application.properties
```

## Getting Started

### Prerequisites

- Java 21+
- Maven

### Build & Run

```sh
mvn spring-boot:run
```

### API Endpoints

- **Transfer Money:**  
  `GET /api/accounts/transfer?fromId={fromId}&toId={toId}&amount={amount}`

### H2 Console

- Access at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
  JDBC URL: `jdbc:h2:mem:testdb`

## Example

On startup, two users and accounts are created:

- User A: Balance = 1000.0
- User B: Balance = 500.0

Transfer money using:

```
GET /api/accounts/transfer?fromId=1&toId=2&amount=100
```

## Transaction Management

- Transfers above 1000 will simulate a rollback.
- Transaction logs are saved in a separate transaction (`REQUIRES_NEW`).

