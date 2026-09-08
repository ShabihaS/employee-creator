# Employee Creator

A full-stack employee management application built with **React + TypeScript** on the frontend and **Spring Boot + MySQL** on the backend.

The application allows users to create, view, update and delete employee records through a RESTful API.

## 🚀 Features

* Create a new employee
* View all employees
* View individual employee details
* Update employee information
* Delete an employee
* Employee contract type selection
* Permanent or fixed-term contract details
* Start and end dates
* Ongoing employment option
* Full-time or part-time employment
* Hours per week
* Personal and contact information
* Form validation on the frontend and backend
* Australian mobile number validation
* RESTful API architecture
* MySQL database persistence
* Responsive user interface

## 🛠️ Tech Stack

### Frontend

* React 19
* TypeScript
* Vite
* React Router
* TanStack React Query
* React Hook Form
* Zod
* SCSS

### Backend

* Java 17
* Spring Boot 4.1.0
* Spring Web MVC
* Spring Data JPA
* Jakarta Bean Validation
* Maven
* MySQL

### Development Tools

* Git & GitHub
* VS Code
* Postman
* MySQL

## 📁 Project Structure

```text
employee-creator/
│
├── employee-creator-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/employeecreator/
│   │   │   │   ├── common/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   │
│   │   │   └── resources/
│   │   │
│   │   └── test/
│   │
│   ├── .env.example
│   ├── pom.xml
│   └── mvnw
│
├── employee-creator-frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── schemas/
│   │   ├── services/
│   │   ├── styles/
│   │   ├── types/
│   │   ├── App.tsx
│   │   └── main.tsx
│   │
│   ├── .env.example
│   ├── package.json
│   └── vite.config.ts
│
└── README.md
```

## 🏗️ Architecture

The application follows a client-server architecture:

```text
┌──────────────────────────────┐
│          Frontend            │
│       React + TypeScript     │
│                              │
│ React Router                 │
│ React Hook Form + Zod        │
│ TanStack React Query         │
└──────────────┬───────────────┘
               │
               │ HTTP / REST API
               ▼
┌──────────────────────────────┐
│           Backend            │
│       Spring Boot API        │
│                              │
│ Controller                   │
│      ↓                       │
│ Service                      │
│      ↓                       │
│ Repository                   │
│      ↓                       │
│ JPA Entity                   │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│           MySQL              │
│       employee_creator       │
└──────────────────────────────┘
```

## 🔌 REST API

The backend API uses the following base URL:

```text
http://localhost:8080/api/employees
```

### Create Employee

```http
POST /api/employees
```

Creates a new employee.

### Get All Employees

```http
GET /api/employees
```

Returns all employees.

### Get Employee by ID

```http
GET /api/employees/{id}
```

Returns a single employee using their ID.

### Update Employee

```http
PUT /api/employees/{id}
```

Updates an existing employee.

### Delete Employee

```http
DELETE /api/employees/{id}
```

Deletes an employee.

## 🗄️ Database Setup

The backend uses MySQL.

Create a database named:

```sql
CREATE DATABASE employee_creator;
```

The backend environment configuration uses:

```text
DB_HOST=localhost
DB_PORT=3306
DB_USER=root
DB_NAME=employee_creator
DB_PASSWORD=
SPRING_PROFILE=dev
```

The actual database password should be stored in the local `.env` file and should not be committed to GitHub.

## ⚙️ Backend Setup

Navigate to the backend directory:

```bash
cd employee-creator-backend
```

Create your local environment file from the example:

```bash
cp .env.example .env
```

Update the database credentials in `.env`.

Then run the Spring Boot application:

### Windows

```bash
.\mvnw.cmd spring-boot:run
```

### macOS / Linux

```bash
./mvnw spring-boot:run
```

The backend will run on:

```text
http://localhost:8080
```

## 💻 Frontend Setup

Open a new terminal and navigate to the frontend:

```bash
cd employee-creator-frontend
```

Install dependencies:

```bash
npm install
```

Create the local environment file:

```bash
cp .env.example .env
```

The frontend API URL is configured as:

```text
VITE_API_URL=http://localhost:8080/api
```

Start the development server:

```bash
npm run dev
```

The Vite development server will provide the local URL in the terminal.

## 🔐 Environment Variables

### Backend

Create:

```text
employee-creator-backend/.env
```

Example:

```env
DB_HOST=localhost
DB_PORT=3306
DB_USER=root
DB_NAME=employee_creator
DB_PASSWORD=your_password
SPRING_PROFILE=dev
```

### Frontend

Create:

```text
employee-creator-frontend/.env
```

Example:

```env
VITE_API_URL=http://localhost:8080/api
```

Environment files containing private credentials should not be committed to the repository.

## ✅ Validation

Validation is handled at multiple levels.

### Frontend

The frontend uses:

* React Hook Form
* Zod

This provides client-side validation and helps prevent invalid form data from being submitted.

### Backend

The backend uses Jakarta Bean Validation with Spring Boot.

The controller validates incoming employee requests before passing them to the service layer.

```java
@Valid @RequestBody EmployeeRequestDTO request
```

This provides an additional validation layer on the server.

## 🧩 Backend Layer Structure

The backend separates responsibilities into different layers:

### Controller

Handles HTTP requests and responses.

```text
EmployeeController
```

### Service

Contains the application's business logic.

```text
EmployeeService
```

### Repository

Handles database access through Spring Data JPA.

### Entity

Represents the employee data stored in the database.

### DTO

Data Transfer Objects are used to control the data sent between the API and clients.

The project uses separate request and response DTOs rather than exposing the entity directly.

## 🎯 Key Learning Outcomes

This project demonstrates practical full-stack development including:

* Designing a RESTful API
* CRUD operations
* Spring Boot application structure
* Spring Data JPA
* MySQL database integration
* DTO-based API design
* Backend validation
* React component architecture
* TypeScript
* React Router
* Form management
* Schema validation
* Server-state management with React Query
* Environment configuration
* Frontend/backend integration
* Git and GitHub workflow

## 🔮 Possible Future Improvements

Potential improvements for a future version could include:

* Authentication and authorization
* Role-based access control
* Employee search and filtering
* Pagination
* Automated testing expansion
* Production deployment
* CI/CD pipeline
* Improved error reporting and monitoring

## 👩‍💻 Author

**Shabiha Sultana**

Junior Software Developer

GitHub: [ShabihaS](https://github.com/ShabihaS)

---

## 📌 Project

Employee Creator is a full-stack application developed to demonstrate the design and implementation of a modern CRUD-based employee management system using React, TypeScript, Spring Boot, and MySQL.
