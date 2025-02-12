# IRCTC-Project_API
# Railway Management System

## 📌 Project Overview
This is a **Railway Management System** built with **Spring Boot** and **MySQL** that allows users to:
- **Register and log in** with authentication.
- **Search for trains** between two stations.
- **Check seat availability** in real-time.
- **Book seats** while handling concurrency (race conditions).
- **Admin functionalities** like adding trains and updating seat availability.

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot, Spring Security, Hibernate
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Tokens) for users, API Key for Admin
- **Concurrency Handling:** Optimistic Locking / Transactions

## ⚡ Setup Instructions
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/your-repo/railway-management.git
cd railway-management
```

### 2️⃣ Configure the Database
Make sure you have MySQL installed and running.

```sql
CREATE DATABASE railway_db;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/railway_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Build and Run the Project
```sh
mvn clean install
mvn spring-boot:run
```

---

## 🔑 Authentication & Security
- **User Login:** JWT token authentication
- **Admin Security:** API Key authentication for admin endpoints

### Sample API Key (Admin Only)
Add `x-api-key` in the headers for admin endpoints:
```json
{
  "x-api-key": "your-secret-api-key"
}
```

---

## 📌 API Endpoints

### 1️⃣ **User Authentication**
#### ✅ **Register a User**
`POST /api/auth/register`
```json
{
  "username": "user123",
  "password": "pass123",
  "role": "USER"
}
```

#### ✅ **Login User**
`POST /api/auth/login`
```json
{
  "username": "user123",
  "password": "pass123"
}
```
_Response:_
```json
{
  "token": "your-jwt-token"
}
```

---

### 2️⃣ **Train & Seat Management**
#### ✅ **Add a Train (Admin Only)**
`POST /api/admin/train`
```json
{
  "trainName": "Express 101",
  "source": "New York",
  "destination": "Chicago",
  "totalSeats": 100
}
```

#### ✅ **Check Train Availability**
`GET /api/trains?source=New York&destination=Chicago`
_Response:_
```json
[
  {
    "trainId": 1,
    "trainName": "Express 101",
    "availableSeats": 50
  }
]
```

#### ✅ **Book a Seat**
`POST /api/bookings`
```json
{
  "trainId": 1,
  "userId": 10
}
```
_Response:_
```json
{
  "message": "Booking successful!"
}
```

---

## 🔥 Handling Race Conditions in Seat Booking
To prevent multiple users from booking the same seat:
- **Optimistic Locking**: Uses Hibernate versioning (`@Version` annotation in entities).
- **Database Transactions**: Ensures seat availability before confirming booking.

---

## 🚀 Future Improvements
- Add **payment integration** for ticket booking.
- Implement **cancellation & refund policies**.
- Improve **real-time notifications** for booking status.

---

## 👨‍💻 Contributors
- **Ashirbad Nayak** - _Backend Developer_

Feel free to contribute to this project! 🛠️

