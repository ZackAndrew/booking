| Column      | Type         | Constraints                | Description            |
| ----------- | ------------ | -------------------------- | ---------------------- |
| id          | BIGINT       | PK, auto-increment         | User unique ID         |
| username    | VARCHAR(50)  | UNIQUE, NOT NULL           | Login username         |
| email       | VARCHAR(100) | UNIQUE, NOT NULL           | User email             |
| password    | VARCHAR(255) | NOT NULL                   | Hashed password        |
| created\_at | TIMESTAMP    | DEFAULT CURRENT\_TIMESTAMP | User registration date |
| enabled     | BOOLEAN      | DEFAULT TRUE               | Account active status  |
