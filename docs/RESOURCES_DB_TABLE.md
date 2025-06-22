| Column      | Type         | Constraints                | Description                       |
| ----------- | ------------ | -------------------------- | --------------------------------- |
| id          | BIGINT       | PK, auto-increment         | Resource unique ID                |
| name        | VARCHAR(100) | NOT NULL                   | Resource name (e.g., Room 101)    |
| description | TEXT         | NULL                       | Optional description              |
| type        | VARCHAR(50)  | NOT NULL                   | Resource type (e.g., ROOM, EVENT) |
| created\_at | TIMESTAMP    | DEFAULT CURRENT\_TIMESTAMP | Creation timestamp                |
