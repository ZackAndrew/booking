| Column       | Type        | Constraints                  | Description                           |
| ------------ | ----------- | ---------------------------- | ------------------------------------- |
| id           | BIGINT      | PK, auto-increment           | Booking unique ID                     |
| user\_id     | BIGINT      | FK → users(id), NOT NULL     | Booking user                          |
| resource\_id | BIGINT      | FK → resources(id), NOT NULL | Booked resource                       |
| start\_time  | TIMESTAMP   | NOT NULL                     | Booking start time                    |
| end\_time    | TIMESTAMP   | NOT NULL                     | Booking end time                      |
| status       | VARCHAR(20) | NOT NULL                     | Booking status (CONFIRMED, CANCELLED) |
| created\_at  | TIMESTAMP   | DEFAULT CURRENT\_TIMESTAMP   | Booking creation timestamp            |
