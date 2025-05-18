# 📝 Java + MySQL Task Manager (CLI)

A simple **command-line to-do list app** built in Java with a MySQL backend. Users can add, list, update, and delete tasks from a persistent database.

---

## ⚙️ Features

- Add new tasks
- View all existing tasks
- Update task titles
- Delete tasks
- All data stored in a **MySQL database**
- Uses **JDBC** (`PreparedStatement`) for safe SQL operations

---

##  Technologies Used

- Java 17+
- JDBC (Java Database Connectivity)
- MySQL 8+
- IntelliJ IDEA (optional, but recommended)

---

##  Database Setup

Run the following SQL script to create the database and table:

```sql
CREATE DATABASE task_manager;

USE task_manager;

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50),
    due_date DATE DEFAULT (CURRENT_DATE + 1),
    is_completed BOOLEAN DEFAULT FALSE
);
