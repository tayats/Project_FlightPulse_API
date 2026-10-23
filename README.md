# ✈️ FlightPulse REST API

[![Java](https://img.shields.io/badge/Java-11-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.18-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![H2 Database](https://img.shields.io/badge/Database-H2_In--Memory-003B57?style=for-the-badge&logo=sqlite&logoColor=white)](https://www.h2database.com/)

A modern, production-grade airline reservation RESTful microservice built with **Java 11**, **Spring Boot**, **Spring Data JPA**, and an embedded **H2 Relational Database**.

The system strictly follows the enterprise **3-Tier Layered Architecture** with full CRUD capabilities, relational entity mapping (`@ManyToOne`), derived search queries, data transfer objects (DTO), and robust HTTP status code handling.

---

## 🏛️ System Architecture (3-Tier Layered Design)

The application separates concerns across distinct layers to maintain high cohesion and loose coupling:

[ Client / Web Browser / Mobile App ] │ ▼ (HTTP / JSON Payloads) ┌─────────────────────────────────────────────────────────┐ │ 1. Presentation Layer (@RestController) │ │ • FlightController, PassengerController, │ │ BookingController │ │ • Request routing, path variables, query parameters │ │ • ResponseEntity status codes & Exception Handling │ └────────────────────────┬────────────────────────────────┘ │ ▼ (DTOs / Java Objects) ┌─────────────────────────────────────────────────────────┐ │ 2. Business Logic Layer (@Service) │ │ • FlightService, BookingService │ │ • Input validation & data normalization │ │ • Business rules & relational orchestration │ └────────────────────────┬────────────────────────────────┘ │ ▼ (Entity Persistence) ┌─────────────────────────────────────────────────────────┐ │ 3. Data Access Layer (Spring Data JPA / @Repository) │ │ • FlightRepository, PassengerRepository, │ │ BookingRepository │ │ • Hibernate ORM, Derived queries without manual SQL │ └────────────────────────┬────────────────────────────────┘ │ ▼ (Relational SQL Execution) ┌─────────────────────────────────────────────────────────┐ │ 4. Persistence Layer (H2 In-Memory Relational Engine) │ │ • Tables: flights, passengers, bookings │ └─────────────────────────────────────────────────────────┘


---

## 🗄️ Relational Data Model

The database schema utilizes Object-Relational Mapping (ORM) powered by Hibernate:

- **`Flight`**: Primary entity storing flight routes, numbers, and base prices.
- **`Passenger`**: Entity storing customer details, passports, and contact emails.
- **`Booking`**: Junction entity modeling the `@ManyToOne` relationships between passengers and flights.

┌────────────────────────┐ ┌────────────────────────┐ │ FLIGHT │ │ PASSENGER │ ├────────────────────────┤ ├────────────────────────┤ │ PK id (IDENTITY) │ │ PK id (IDENTITY) │ │ flight_number │ │ first_name │ │ origin │ │ last_name │ │ destination │ │ passport_number │ │ base_price │ │ email │ └───────────┬────────────┘ └───────────┬────────────┘ │ 1 │ 1 │ │ │ ∞ │ ∞ ┌────┴───────────────────────────────────────────┴────┐ │ BOOKING │ ├─────────────────────────────────────────────────────┤ │ PK id (IDENTITY) │ │ FK flight_id │ │ FK passenger_id │ │ booking_reference │ │ paid_fare │ │ status │ └─────────────────────────────────────────────────────┘


---

## 📡 REST API Specification

### 1. Flights API (`/api/flights`)

| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/flights` | Fetch all registered flights | `200 OK` |
| **GET** | `/api/flights/{id}` | Fetch a specific flight by ID | `200 OK`, `404 Not Found` |
| **GET** | `/api/flights/search?origin={origin}` | Search flights by origin using derived JPA query | `200 OK` |
| **POST** | `/api/flights` | Create a new flight route (validates price & normalizes code) | `201 Created`, `400 Bad Request` |
| **PUT** | `/api/flights/{id}` | Update flight details | `200 OK`, `404 Not Found` |
| **DELETE** | `/api/flights/{id}` | Remove a flight route | `204 No Content`, `404 Not Found` |

### 2. Passengers API (`/api/passengers`)

| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/passengers` | Fetch all registered passengers | `200 OK` |
| **GET** | `/api/passengers/{id}` | Fetch passenger by ID | `200 OK`, `404 Not Found` |
| **POST** | `/api/passengers` | Register a new passenger | `201 Created` |
| **DELETE** | `/api/passengers/{id}` | Delete a passenger | `200 OK`, `404 Not Found` |

### 3. Bookings API (`/api/bookings`)

| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/bookings` | Fetch all confirmed bookings with nested flight & passenger | `200 OK` |
| **GET** | `/api/bookings/passenger/{passengerId}` | Fetch all bookings made by a specific passenger | `200 OK` |
| **POST** | `/api/bookings` | Book a flight using `BookingRequest` DTO | `201 Created`, `400 Bad Request` |

---

## ✨ Key Technical Highlights

1. **Enterprise 3-Tier Layering**: Clear demarcation of responsibilities between Controllers, Services, and Repositories via Spring Dependency Injection (`@Autowired` / Constructor Injection).
2. **Spring Data JPA Derived Queries**: Custom filtering (`findByOrigin`, `findByPassengerId`) generated entirely by method naming conventions with **zero SQL boilerplate**.
3. **Data Transfer Object (DTO) Pattern**: `BookingRequest` decouples network input payloads from internal entity models.
4. **Resilient Error Handling**: Centralized exception interceptors (`@ExceptionHandler`) converting `IllegalArgumentException` into clean `400 Bad Request` responses rather than unhandled 500 crashes.
5. **Auto Schema Generation & SQL Inspection**: Hibernate automatically generates and updates tables on startup (`ddl-auto=update`) and logs formatted SQL statements in real time.

---

## 🛠️ Getting Started

### Prerequisites
- **Java**: Adoptium JDK 11 (or higher)
- **Maven**: 3.6+ (or bundled Maven wrapper)

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/tayats/Project_FlightPulse_API.git
   cd Project_FlightPulse_API