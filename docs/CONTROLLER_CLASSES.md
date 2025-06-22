| Controller         | Responsibility                  | Typical Endpoints                                          | Access         |
|--------------------|--------------------------------|------------------------------------------------------------|----------------|
| **AuthController**  | User registration & login      | `POST /api/auth/register`, `POST /api/auth/login`          | Public         |
| **UserController**  | User profile management        | `GET /api/users/me`, `PUT /api/users/me`, (optional) `GET /api/users/{id}` (admin only) | Authenticated  |
| **ResourceController** | CRUD on bookable resources   | `GET /api/resources`, `GET /api/resources/{id}`, `POST /api/resources` (admin), `PUT /api/resources/{id}` (admin), `DELETE /api/resources/{id}` (admin) | User / Admin   |
| **BookingController** | User booking management      | `GET /api/bookings/my`, `POST /api/bookings`, `DELETE /api/bookings/{id}` (owner/admin) | Authenticated  |
| **AdminController** | Admin management               | `GET /api/admin/users`, `DELETE /api/admin/users/{id}`, `GET /api/admin/bookings`, `DELETE /api/admin/bookings/{id}`, (optional) `GET /api/admin/dashboard` | Admin only     |
