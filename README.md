# Booking System REST API

A simple REST API for user management and booking resources.  
Built with **Spring Boot**, **Spring Data JPA**, and **JWT Authentication**.

---

## Features
- User registration & login with JWT authentication  
- Resource management (create, list, update, delete)  
- Role separation (admin endpoints under `/admin/...`)  
- Error handling with meaningful HTTP status codes  

---

## Tech Stack
- Java 17 
- Spring Boot  
- Spring Web, Spring Data JPA  
- JWT (JSON Web Token)  
- PostgreSQL
- Maven  

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+  
- Maven  
- MySQL (or H2 for testing)

### Installation
```bash
git clone <repo-url>
cd booking
mvn spring-boot:run
