
# Expense Tracker API

A simple Spring Boot REST API to manage expenses. Supports CRUD operations, CSV export, and PDF report generation.

## 📦 Technologies Used

- Java 17+
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL (for database)
- iTextPDF (for PDF generation)
- Thymeleaf (optional)
- Maven

---

## 🚀 Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-repo/expense-tracker-api.git
cd expense-tracker-api
````

### 2️⃣ Configure Database

In `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

Ensure PostgreSQL is running and the database `expense_db` exists.

### 3️⃣ Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## 🛠 API Endpoints

| Method | Endpoint                   | Description                     |
| ------ | -------------------------- | ------------------------------- |
| POST   | `/api/expenses`            | Add a new expense               |
| GET    | `/api/expenses`            | List all expenses               |
| GET    | `/api/expenses/{id}`       | Get expense by ID               |
| PUT    | `/api/expenses/{id}`       | Update an expense               |
| DELETE | `/api/expenses/{id}`       | Delete an expense               |
| GET    | `/api/expenses/filter`     | Filter expenses (category/date) |
| GET    | `/api/expenses/summary`    | Expense summary report          |
| GET    | `/api/expenses/export/csv` | **Export all expenses to CSV**  |
| GET    | `/api/expenses/export/pdf` | **Export all expenses to PDF**  |

---

## 📄 CSV Export

* Endpoint:
  `GET /api/expenses/export/csv`

* Description:
  Generates a downloadable CSV file with all expenses data.

* Working:

  * Sets response header:

    ```
    Content-Disposition: attachment; filename=expenses.csv
    ```
  * Loops through all expenses and writes CSV-formatted data.

---

## 📄 PDF Report Generation

* Endpoint:
  `GET /api/expenses/export/pdf`

* Description:
  Generates a downloadable PDF expense report.

* Working:

  * Uses **iTextPDF 7** to:

    * Create a `PdfDocument` and `Document`.
    * Add report title.
    * Add a table containing expense details.
    * Write directly to HTTP response output stream.

---

## 📬 Testing the API (Optional)

* Use Postman or any REST client.
* For CSV and PDF export:

  * Open browser or Postman.
  * Call `/api/expenses/export/csv` or `/api/expenses/export/pdf`.
  * File will download automatically.

---

## 🔒 Security

Currently **open endpoints**. You can add **Spring Security** to protect APIs.

---

## 📈 Next Possible Improvements

* Add Swagger API documentation.
* Add authentication using JWT.
* Build a simple frontend (React/Thymeleaf).
* Deploy to cloud (AWS, Heroku, etc).

---

## 🛡 License

This project is licensed under MIT License.

---

## ✍️ Author

Chhabinath Sahoo

