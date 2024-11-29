# VRV Security

## Description

VRV Security is a Spring Boot-based application that implements **Role-Based Access Control (RBAC)**, **JWT authentication**, and secure **password hashing** using **Argon2**. It integrates with **PostgreSQL** for data storage and follows industry-standard security practices to protect sensitive user data.

The application provides secure user registration, login mechanisms, and role-based access to different system resources. Security is a core focus of this application, ensuring that user data is safely handled and sensitive endpoints are accessible only to authorized users.

## Key Technologies Used

- **Spring Boot**: A framework for building production-ready backend applications with minimal configuration. It provides an easy setup and an embedded Tomcat server to run the app.
- **Spring Security**: A powerful and customizable authentication and access control framework that is integrated into Spring Boot applications. It handles JWT-based authentication, role-based access control, and secure password storage.
- **PostgreSQL**: A relational database used for storing user information and roles. It is configured to ensure secure storage of sensitive data like usernames, roles, and hashed passwords.
- **Argon2**: A password hashing algorithm used to securely store user passwords. Argon2 is known for being memory-intensive, making it more resistant to brute-force and GPU-based cracking attempts.
- **JWT (JSON Web Tokens)**: A compact, URL-safe means of representing claims between two parties. JWT is used for stateless authentication, ensuring that users can authenticate and authorize without maintaining a session on the server.
- **Spring Data JPA**: A part of the Spring Framework that simplifies database interactions by allowing developers to use Java objects to represent database records and perform queries.

## Why Argon2?

Argon2 is used for password hashing because it provides robust security features:

- **Memory-Hard Algorithm**: Unlike traditional hashing algorithms, Argon2 requires a significant amount of memory to compute, making it much harder for attackers to use specialized hardware (such as GPUs or ASICs) for brute-force attacks.
- **Configurable Security Parameters**: Argon2 allows developers to adjust the time cost, memory cost, and parallelism factors. This customization enables you to balance security and performance based on the system's resources.
- **Resistance to Modern Attack Techniques**: Argon2 is designed to withstand both brute-force and rainbow table attacks, ensuring that user passwords are stored in a highly secure manner.
- **Industry Standard**: Argon2 has been recognized as the winner of the Password Hashing Competition (PHC) and is widely regarded as one of the most secure hashing algorithms available today.

## Features

- **User Authentication**: Secure registration and login system using JWT for stateless authentication.
- **Role-Based Access Control (RBAC)**: Different user roles (e.g., Admin, User) with specific access rights to resources.
- **Secure Password Hashing**: Passwords are securely hashed using the Argon2 algorithm, ensuring they are protected against brute-force and rainbow table attacks.
- **Token-Based Authentication**: Stateless authentication using JWT tokens for API access, avoiding the need for traditional session-based management.
- **Secured Endpoints**: Each endpoint is protected based on user roles. For example, admin endpoints are restricted to users with the `ROLE_ADMIN` role.
- **Cross-Origin Resource Sharing (CORS)**: Configured to support API requests from different origins, commonly used in frontend-backend applications.
- **CSRF Protection**: Disabled, as the application relies on JWT-based authentication rather than traditional session management.

## Setup and Installation

### Prerequisites
- **Java 11 or later**: Spring Boot requires Java 11 or newer for building and running the application.
- **PostgreSQL Database**: A PostgreSQL database is needed to store user and role information.
- **Maven**: The application uses Maven for building and managing dependencies.

### Step-by-Step Setup

1. **Clone the Repository**:
   Clone the repository to your local machine:
   ```bash
   git clone https://github.com/NAVEENDER2005/vrv-rbac.git
   cd vrv-rbac
Set up PostgreSQL Database:

Install PostgreSQL if it is not already installed on your machine.
Create a database for the application:
sql
Copy code
CREATE DATABASE vrv_security;
Configure the database settings in application.properties (located in src/main/resources):
properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/vrv_security
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
Build and Run the Application:

Build the project using Maven:
bash
Copy code
./mvnw clean install
Run the Spring Boot application:
bash
Copy code
./mvnw spring-boot:run
The application will be available at http://localhost:8080.
Usage
User Registration Endpoint
Endpoint: POST /register
Request Body:
json
Copy code
{
  "username": "user1",
  "password": "strongpassword",
  "role": "ROLE_USER"
}
Description: Registers a new user with a secure password and assigns them a role (ROLE_USER or ROLE_ADMIN).
User Login Endpoint
Endpoint: POST /login
Request Body:
json
Copy code
{
  "username": "user1",
  "password": "strongpassword"
}
Response:
json
Copy code
{
  "token": "JWT_TOKEN"
}
Description: Authenticates a user and returns a JWT token. The token can be used for subsequent requests to secured endpoints.
Accessing Secured Endpoints
Use the JWT token obtained from the login request to access protected resources. Example request:
http
Copy code
GET /secure-data HTTP/1.1
Host: localhost:8080
Authorization: Bearer <your_jwt_token>
Role-Based Authorization Example
Admin users: @PreAuthorize("hasRole('ROLE_ADMIN')")
Regular users: @PreAuthorize("hasRole('ROLE_USER')")
Access: Role-based access control restricts users from accessing endpoints outside of their assigned role.
Security Highlights
Password Hashing: Argon2
Why Argon2?
Resistant to Brute Force and GPU Cracking: Argon2's memory-hard nature makes it very difficult for attackers to use GPUs or specialized hardware to crack passwords.
Customizable Security: Argon2 allows adjusting time cost, memory cost, and parallelism to suit your security needs.
Industry-Standard Security: Argon2 is the winner of the Password Hashing Competition (PHC) and is considered one of the most secure hashing algorithms.
JWT Authentication
Stateless Authentication: JWT tokens are used to authenticate users without maintaining sessions on the server. This makes the application scalable and efficient.
JWT Expiry: Tokens have an expiration time, reducing the risk of long-term unauthorized access if a token is compromised.
Role-Based Access Control (RBAC)
Role Definition: Users can be assigned roles like ROLE_USER, ROLE_ADMIN during registration.
Endpoint Protection: Access to sensitive endpoints is restricted based on the user’s role. For example, only admin users can access /admin endpoints.
Cross-Site Request Forgery (CSRF) Protection
CSRF protection is disabled because the application uses JWT tokens for authentication, which do not rely on cookies or sessions. CSRF protection is typically used in session-based authentication.
Cross-Origin Resource Sharing (CORS)
The application is configured with CORS to allow cross-origin requests, enabling frontend applications hosted on different servers to access the backend API.
SQL Injection Prevention
JPA Repositories: Spring Data JPA uses parameterized queries to prevent SQL injection vulnerabilities, ensuring that user input is properly handled and sanitized.
Conclusion
VRV Security is a robust, secure Spring Boot application that integrates modern security practices. By combining JWT authentication, Argon2 password hashing, and RBAC, this application ensures that sensitive user data is protected and only authorized users can access certain resources. The use of PostgreSQL for persistent data storage ensures reliable and secure management of user information.

Feel free to contribute to the project, raise issues, or suggest improvements through GitHub issues and pull requests.


