# 📚 Online Bookstore Backend

A backend application for managing an online bookstore. This system handles book inventory, pricing logic, purchases, and customer loyalty points.

---

## 🚀 Features

- 📦 **Inventory Management**  
  Add, update, or remove books from inventory.

- 💰 **Dynamic Pricing**  
  Pricing adjusts based on:
  - Book type (New, Regular, Old Edition)
  - Purchase size (bundles of 3+ books)
  - Loyalty discounts (every 10 points = 1 free book)

- 🎁 **Customer Loyalty**  
  Customers earn 1 point per book. Every 10 points grants a free regular or old-edition book.

- 🛒 **Order Processing**  
  Buy one or more books and get the final price calculated with applicable discounts.

- 📘 **Swagger UI Integration**  
  Easily explore and test the API with interactive docs.

---

## ⚙️ Tech Stack

- **Java 21**
- **Spring Boot 3**
- **Jakarta Persistence (JPA)**
- **Liquibase** for DB migrations
- **PostgreSQL**
- **Swagger/OpenAPI** via Springdoc

---

## ▶️ How to Run

1. **Clone the repository**

   ```bash
   git clone https://github.com/giorobota/bookstore.git
   ```

2. **Set up the database**  
   Make sure PostgreSQL is running and a database is created.

3. **Configure application.yml**

   Update `src/main/resources/application.yml` with your DB credentials.

4. **Run the app**

   ```bash
   ./gradlew bootRun
   ```

5. **Access Swagger UI**

   Visit: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

    Or use endpoints with browser/postman
---

## 📄 Design Notes

- Entity relationships follow normalized structure (`Book`, `BookInventory`, `Customer`, `Order`).
- Pricing and loyalty rules are encapsulated for easy extensibility.
- JSON-based book purchases in `Order` allow future flexibility.
- Liquibase is used to version database changes and insert sample data.

---
