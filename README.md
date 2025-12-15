# 🍔 Tasty Bites - Online Food Ordering System

A robust e-commerce web application built with **Java Jakarta EE**, **JSP**, **JSTL**, and **RESTful Web Services**. This application allows users to browse a menu, add items to a shopping cart, manage quantities, and proceed to checkout.

---

## 🛠️ Tech Stack

* **Backend:** Java (Jakarta EE / J2EE), JAX-RS (REST API), Servlets
* **Frontend:** JSP (JavaServer Pages), JSTL, Bootstrap 5, HTML5/CSS3
* **Database:** MySQL (or PostgreSQL)
* **Build Tool:** Apache Maven
* **Server:** Apache Tomcat 10+ (Recommended) or GlassFish/WildFly

---

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

1.  **Java Development Kit (JDK) 11 or higher**
2.  **Apache Maven** (for building the project)
3.  **Apache Tomcat 10.1.x** (Jakarta EE 9/10 compatible server)
4.  **MySQL Server** (or your preferred SQL database)
5.  **IDE** (IntelliJ IDEA, Eclipse, or NetBeans)

---

## 🚀 Setup & Installation

### 1. Database Configuration

1.  Open your SQL client (e.g., MySQL Workbench).
2.  Create the database:
    ```sql
    CREATE DATABASE restaurant_db;
    USE restaurant_db;
    ```
3.  Create the `menu_items` table:
    ```sql
    CREATE TABLE menu_items (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        description TEXT,
        price DECIMAL(10, 2) NOT NULL,
        image_path VARCHAR(255)
    );
    ```
4.  Insert dummy data so the menu isn't empty:
    ```sql
    INSERT INTO menu_items (name, description, price, image_path) VALUES 
    ('Classic Burger', 'Juicy beef patty with lettuce and tomato', 12.50, '[https://via.placeholder.com/150](https://via.placeholder.com/150)'),
    ('Margherita Pizza', 'Fresh basil, mozzarella, and tomato sauce', 15.00, '[https://via.placeholder.com/150](https://via.placeholder.com/150)'),
    ('Caesar Salad', 'Crispy romaine with parmesan and croutons', 9.99, '[https://via.placeholder.com/150](https://via.placeholder.com/150)');
    ```

### 2. Configure Database Connection

Open `src/main/resources/META-INF/persistence.xml` (if using JPA) or your `DatabaseUtil.java` class and update the credentials:

```java
// Example in DatabaseUtil.java
private static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
private static final String USER = "root"; // restaurant_db 
private static final String PASSWORD = "password"; // root

<dependencies>
    <dependency>
        <groupId>jakarta.platform</groupId>
        <artifactId>jakarta.jakartaee-api</artifactId>
        <version>10.0.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.glassfish.web</groupId>
        <artifactId>jakarta.servlet.jsp.jstl</artifactId>
        <version>3.0.1</version>
    </dependency>
    <dependency>
        <groupId>jakarta.servlet.jsp.jstl</groupId>
        <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
        <version>3.0.0</version>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
</dependencies>