# Contact Management App - CodeNova Java Internship (Task 1)

## 📌 Project Overview
The **Contact Management Application** is a Java Swing desktop application built to securely add, view, search, update, and delete contact records. It uses an embedded relational database (Apache Derby) and follows an object-oriented, modular architecture separating data models, database operations, and graphical user interfaces.

## 🚀 Core Features & Requirements
* **CRUD Operations:** Seamlessly add, view, update, and delete contact entities from the database.
* **Advanced Search:** Filter and search contacts dynamically by name or phone number.
* **Strict Validation:** 
  * Regular expression (Regex) verification for valid email syntax.
  * Length and numerical checks enforcing exact 10-digit mobile numbers.
* **Persistent Storage:** Connects to a local Apache Derby database via JDBC `PreparedStatement` and `ResultSet`.
* **Modular MVC Structure:** Organized into distinct classes:
  * **Model (`ContactDetails.java`):** Represents individual contact entities.
  * **Service/DAO (`ContactProcessor.java`):** Handles database connectivity and SQL queries.
  * **View (`ContactManagementAppGUI.java`):** Manages the Java Swing graphical user interface.

## 🛠️ Tech Stack
* **Language:** Java 17+
* **GUI Framework:** Java Swing (`JFrame`, `JTable`, `JComboBox`, `JTextField`, `JMenuBar`)
* **Database:** Apache Derby (`jdbc:derby://localhost:1527/contactsDB`)
* **Utilities:** `java.time.LocalDate`, Regular Expressions (`java.util.regex`)

---

## ⚙️ Setup & Execution Guide

### 1. Database Configuration
* Ensure your local Apache Derby server is running on port `1527` with the database `contactsDB` created.
* Default connection credentials: Username: `app` | Password: `app`.

### 2. Running via IDE (NetBeans / Eclipse / IntelliJ)
* Open the `ContactManagementApp` project folder in your IDE.
* Locate and run the main class: `codenova.taskOne.ContactManagementAppGUI`.

### 3. Running via Command Line (`javac`)
To compile and execute directly from Command Prompt (`cmd`) or terminal:

1. Navigate to the project's `src` folder (where the `codenova` package directory lives):
   ```cmd
   cd "Contact Management App\ContactManagementApp\src"
   ```

2. Compile all package Java files:
   * **Windows Command Prompt:**
     ```cmd
     javac codenova\taskOne\ContactDetails.java codenova\taskOne\ContactProcessor.java codenova\taskOne\ContactManagementAppGUI.java
     ```
   * **Linux / macOS Terminal:**
     ```bash
     javac codenova/taskOne/*.java
     ```

3. Run the compiled application:
   ```cmd
   java codenova.taskOne.ContactManagementAppGUI
   ```

---

## 📷 Execution Screenshots

### 1. Main Application Interface
<img width="937" height="778" alt="View All button results" src="https://github.com/user-attachments/assets/52383503-9290-4344-bd72-72f374bc7bfa" />


### 2. Search & Table Results
<img width="937" height="778" alt="search via partial name" src="https://github.com/user-attachments/assets/1aa4f81c-2e39-4049-9935-7d6e4dd0f578" />
<img width="937" height="778" alt="search via partial phone number" src="https://github.com/user-attachments/assets/7205feea-90de-419b-981c-9fd3efa0f8bc" />


---
*Developed by Nicole as part of the CodeNova Java Programming Internship (Batch 01).*
