VRV Security
Description
This is a Spring Boot-based application focusing on security, implementing role-based access control (RBAC) with JWT authentication and secure password hashing using Argon2. The application integrates PostgreSQL for data storage and employs industry-standard security practices to protect sensitive user data.

Key Technologies Used
Spring Boot: Framework for building the backend of the application.
Spring Security: For authentication, authorization, and overall security.
PostgreSQL: Database used to store user information and roles.
**Argon2: Secure password hashing technique.**

Argon2 is used for password hashing because it is secure, resistant to brute-force attacks, and optimized for modern hardware. It is a memory-hard algorithm, making it difficult for attackers to use GPUs or specialized hardware to crack passwords. Argon2 offers customizable parameters like time cost, memory cost, and parallelism, allowing developers to adjust the security level based on the system’s resources. Its design and resistance to modern attack methods make it a top choice for securing passwords.







JWT (JSON Web Tokens): Used for secure, stateless authentication.
Spring Data JPA: For database interaction and ORM (Object-Relational Mapping).
Features
User Authentication: Secure registration and login mechanism with JWT.
Role-Based Access Control (RBAC): Define user roles (e.g., Admin, User) and restrict access based on roles.
Password Hashing (Argon2): Passwords are stored securely using the Argon2 algorithm, resistant to brute-force and rainbow table attacks.
Token-Based Authentication: Stateless authentication using JWT tokens for API access.
Secured Endpoints: Role-based access control for each endpoint, ensuring that only authorized users can access sensitive resources.
CORS and CSRF Protection: Configured for cross-origin requests and disabled CSRF for JWT-based authentication.
Setup and Installation
Prerequisites
Java 11 or later: Required for building and running the Spring Boot application.
PostgreSQL Database: For storing user and role information.
Maven: For building the application.
Step-by-Step Setup
Clone the repository: git clone https://github.com/NAVEENDER2005/vrv-rbac.git cd vrv-rbac

Set up PostgreSQL Database:

Create a PostgreSQL database.
Configure the database settings in application.properties.
Run the Application:

Build and run the application: ./mvnw spring-boot:run
The app will be accessible at http://localhost:8080.
Usage
User Registration
Endpoint: POST /register
Request Body: { "username": "user1", "password": "strongpassword", "role": "ROLE_USER" }
User Login
Endpoint: POST /login
Request Body: { "username": "user1", "password": "strongpassword" }
Response: { "token": "JWT_TOKEN" }
Accessing Secured Endpoints
Use the token obtained from login to access secured endpoints.
Example:
Request to /secure-data with Authorization: Bearer <your_token>
Role-Based Authorization
Example: Restrict access to certain endpoints based on user roles.
Admin users: @PreAuthorize("hasRole('ROLE_ADMIN')")
Regular users: @PreAuthorize("hasRole('ROLE_USER')")
Security Highlights
1. Password Hashing:
Argon2: This application uses Argon2PasswordEncoder from Spring Security to hash passwords securely.
Benefits:
Highly resistant to brute-force attacks.
Resilient against GPU-based cracking attempts.
Adjustable parameters for security customization.
2. JWT Authentication:
Stateless Authentication: No session is maintained on the server, enhancing scalability.
JWT Expiry: Tokens expire after a set period, reducing the risk of unauthorized access.
3. Role-Based Access Control (RBAC):
User Roles: Users can be assigned roles (ROLE_USER, ROLE_ADMIN, etc.) during registration.
Access Restrictions: Endpoints are protected based on the user's role using @PreAuthorize annotations.
4. Cross-Site Request Forgery (CSRF):
Disabled in the application since JWT tokens handle authentication and authorization, and CSRF protection is typically required for session-based authentication.
5. Cross-Origin Resource Sharing (CORS):
CORS Configuration: Properly configured for API access from different origins (e.g., frontend apps).
6. SQL Injection Prevention:
JPA Repositories: Uses parameterized queries, preventing SQL injection vulnerabilities.
