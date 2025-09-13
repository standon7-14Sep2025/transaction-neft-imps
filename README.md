# Multi-Database Transaction API

This Spring Boot project provides RESTful APIs for handling NEFT and IMPS transactions, with support for multiple database connections and Kafka integration.

## 🔧 Features

- **Multi-Database Support**:  
  Connects to both **PostgreSQL** and **Oracle** databases using Spring Data JPA and custom configuration.

- **NEFT Transactions via Kafka**:  
  NEFT transaction requests are published to a Kafka topic for asynchronous processing.

- **IMPS Transactions via RESTTemplate**:  
  IMPS transactions are handled through REST calls to external services using Spring's `RestTemplate`.

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Apache Kafka
- PostgreSQL
- Oracle DB
- REST APIs
- Maven

## 📁 Project Structure

- `src/main/java/.../controller` – REST endpoints
- `src/main/java/.../service` – Business logic
- `src/main/java/.../config` – Kafka and DB configuration
- `src/main/resources/application.yml` – Environment settings

## 🛠️ Setup Instructions

1. Clone the repository
2. Configure database credentials in `application.yml`
3. Ensure Kafka is running and accessible
4. Build and run the application:
   ```bash
   mvn spring-boot:run