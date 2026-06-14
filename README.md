# QuickBite Food Delivery Backend System

A production-style RESTful API built using Spring Boot that simulates a real-world food delivery platform backend. It supports secure authentication, restaurant management, menu management, cart operations, and order processing workflows.

## 🚧 Project Status

🔧 Actively under development — continuously improving with production-ready backend features.

## 🔑 Features (Completed)
- User Registration & Login using JWT Authentication
- Role-Based Access Control (Admin, Customer, Restaurant Owner, Delivery Partner)
- Restaurant Management APIs
- Category Management APIs
- Food Item / Menu Management APIs
- Cart Management System
- Order Placement & Order Tracking
- Order Status Management
- DTO-based API Architecture
- Global Exception Handling
- Spring Security Integration

## 🚧 Upcoming Features

- 📄 Swagger/OpenAPI Documentation
- ✔ Request Validation using Jakarta Validation
- 📑 Pagination & Sorting
- 🔍 Restaurant & Food Search APIs
- 🐳 Docker Containerization
- 💳 Payment Gateway Integration
- 📧 Email Notifications

## 🛠 Tech Stack
- ☕ Java 17
- 🌱 Spring Boot
- 🔐 Spring Security + JWT
- 🗄 Spring Data JPA
- ⚡ Hibernate
- 🛢 MySQL
- 🧪 Postman (API Testing)
- 📦 Maven


  ## 🧠 Key Highlights

- ✔ Implemented secure JWT-based authentication and authorization
- ✔ Designed layered architecture (Controller → Service → Repository)
- ✔ Developed complete food ordering workflow from Cart to Order Processing
- ✔ Modeled complex JPA entity relationships using Hibernate
- ✔ Implemented Global Exception Handling for consistent API responses
- ✔ Built secure REST APIs following backend development best practices

## 📌 User Roles
- **CUSTOMER**
- Register & Login
- Browse Restaurants
- View Menu Items
- Add Items to Cart
- Place Orders
- View Order History

- **RESTAURANT_OWNER**
- Manage Restaurants
- Manage Categories
- Manage Food Items
- Update Order Status

- **ADMIN**
- Manage System Resources
- Monitor Platform Operations

- **DELIVERY_PARTNER**
Reserved for future delivery management features


## 📁 Project Structure
- `quickbite`

- `src/main/java`

- `com.tech.quickbite`

- **controller**      - REST Controllers
- **dto**             - Request & Response DTOs
- **entity**          - Entity Classes
- **enums**           - Enum Definitions
- **exception**       - Custom Exceptions
- **repository**      - JPA Repositories
- **security**        - JWT & Security Configuration
- **service**         - Service Interfaces
- **serviceimpl**     - Service Implementations

- `src/main/resources`
- `application-example.properties`
- `target`

  
## ⚙️ How to Run
### 1. Clone Repository
``` bash
git clone https://github.com/Dishikamewada-01/quickBite-food-delivery-backend.git
cd quickBite-food-delivery-backend
```

### 2. Open in IDE
``` bash
Import the project as a Maven Project in Eclipse or IntelliJ IDEA.
Wait until all dependencies are downloaded.
```

### 3. Configure Database

Rename:
application-example.properties

to:
application.properties

Update database credentials:

``` bash
spring.datasource.url=jdbc:mysql://localhost:3306/quickbite
spring.datasource.username=root
spring.datasource.password=your_password

jwt.secret=your_secret_key

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

### 4. Create Database
``` bash
CREATE DATABASE quickbite;
```

### 5. Run Application
``` bash
Run:

QuickbiteApplication.java

Application will start at:

http://localhost:8080

```


## 📌 Major API Modules
- **Authentication**
- POST /api/auth/register
- POST /api/auth/login
  
- **Restaurant**
- Create Restaurant
- View Restaurants

- **Category**
- Create Category
- View Categories
- Food Item
- Add Food Item
- View Menu
- Delete Food Item

- **Cart**
- Add To Cart
- View Cart
- Remove Cart Item
- Clear Cart

- **Order**
- Place Order
- Get My Orders
- Get Order By Id
- Update Order Status

## 👩‍💻 Author
Dishika Mewada
